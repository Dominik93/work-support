package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.slusarz.worksupport.module.logdownloader.domain.CurrentFileLogChannelResult;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpGetRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@ToString
@AllArgsConstructor(staticName = "of")
public class SftpLogFileDownloader implements SftpChannelExecutable {

    @NonNull
    private SftpGetRequest sftpGetRequest;

    @Override
    public SftpChannelResult execute(final ChannelSftp channelSftp) {
        return CurrentFileLogChannelResult.of(downloadLog(channelSftp));
    }

    private CurrentLogFile downloadLog(ChannelSftp channelSftp) {
        try {
            FtpMetadata sourceFile = sftpGetRequest.getSourceFile();

            log.info("sourceFile "+ sourceFile.getFullPath());
            InputStream inputStream = channelSftp.get(sourceFile.getFullPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = inputStream.read(buf)) >= 0)
                baos.write(buf, 0, n);
            byte[] content = baos.toByteArray();
            return CurrentLogFile.of(sourceFile.getName(), sourceFile.getServer(), content);
        } catch (SftpException | IOException e) {
            log.info("error " +  e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
