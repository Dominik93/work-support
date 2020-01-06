package com.slusarz.worksupport.module.logdownloader.application.livelog;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.application.processors.line.SimpleLineProcessor;
import com.slusarz.worksupport.module.logdownloader.application.processors.text.SimpleTextProcessor;
import com.slusarz.worksupport.module.logdownloader.application.processors.text.TextProcessor;
import com.slusarz.worksupport.module.logdownloader.domain.LiveLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import com.slusarz.worksupport.ssh.domain.channel.VoidSftpChannelResult;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ToString
public class LiveLogger implements SftpChannelExecutable {

    private TextProcessor processor = new SimpleTextProcessor(new SimpleLineProcessor());

    private int countWithoutPop = 0;
    private LiveLog liveLog;
    private boolean end;
    private boolean wait;

    private Map<Application, String> contents = new HashMap<>();
    private Map<Application, Long> skip = new HashMap<>();

    public LiveLogger(final LiveLog liveLog) {
        this.liveLog = liveLog;
        for (Application application : this.liveLog.getApplications()) {
            contents.put(application, "");
        }
        log.info("init " + contents);
    }

    @Override
    public SftpChannelResult execute(ChannelSftp channelSftp) {
        try {
            while (!end && !wait && countWithoutPop < 60) {
                for (Application application : contents.keySet()) {

                    DirectoryPath directoryPath
                            = DirectoryPathFactory.ofRemoteArchivePath(application, application.getServers().get(0));
                    FtpMetadata source
                            = FtpMetadata.of(application.getCurrentLogFileName(), directoryPath,  application.getServers().get(0)); // TODO: do poprawy
                    SftpATTRS stat = channelSftp.stat(source.getFullPath());
                    long size = stat.getSize();
                    Long previous = skip.put(application, size);
                    if (Objects.isNull(previous)) {
                        previous = size;
                    }
                    if (size != previous) {
                        log.info("file size " + size);
                        log.info("previous file size " + previous);
                        InputStream inputStream = channelSftp.get(source.getFullPath(), null, previous);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];
                        int n;
                        while ((n = inputStream.read(buf)) >= 0) {
                            baos.write(buf, 0, n);
                        }
                        StringWriter stringWriter = new StringWriter();
                        Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()));
                        processor.process(stringWriter, new BufferedReader(reader), toLogQuery(liveLog));
                        contents.put(application, contents.get(application) + "" + stringWriter.toString());
                    }
                }
                log.info("wait");
                Thread.sleep(5000);
                countWithoutPop++;
            }
        } catch (SftpException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return VoidSftpChannelResult.empty();
    }

    private LogQuery toLogQuery(LiveLog liveLog) {
        return LogQuery.of(liveLog.getQueries(), liveLog.isOnlyException(), liveLog.getBufferSize());
    }

    public void end() {
        log.info("end");
        this.end = true;
    }

    public Map<Application, String> popContent() {
        log.info("popContent");
        wait = true;
        countWithoutPop = 0;
        Map<Application, String> applicationStringMap = new HashMap<>(contents);
        contents.replaceAll((application, s) -> "");
        wait = false;
        return applicationStringMap;
    }

}
