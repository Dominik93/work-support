package com.slusarz.worksupport.module.logdownloader.application.archive;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.module.logdownloader.domain.unzip.UnzipRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class UnzipRequestsPreparator {

    @Provide
    private EnvironmentProvider environmentProvider;

    @Provide
    private LogDownloaderConfiguration logDownloaderConfiguration;

    public Map<Format, List<UnzipRequest>> prepareUnzipRequests(DownloadLog downloadLog) {
        Map<Format, List<UnzipRequest>> unzipRequests = new HashMap<>();

        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                for (LocalDateTime localDateTime : downloadLog.getDates()) {
                    DirectoryPath localPath
                            = DirectoryPathFactory.ofLocalPath(logDownloaderConfiguration.getLogDirectory(), environmentProvider.provide(), server);

                    FileName logFileName = application.getLogFileName(localDateTime);
                    FtpMetadata destination = FtpMetadata.of(logFileName, localPath, server);
                    FileName archiveFileName = application.getArchiveFileName(localDateTime);
                    FtpMetadata source = FtpMetadata.of(archiveFileName, localPath, server);

                    log.info("Source [" + source + "]");
                    log.info("Destination [" + destination + "]");
                    if (Files.exists(source.getPath())) {
                        UnzipRequest unzipRequest = UnzipRequest.of(source, destination);
                        log.info("Create unzipRequest [" + unzipRequest + "]");
                        addUnzipRequest(unzipRequests, application, unzipRequest);
                    }
                }
            }
        }
        return unzipRequests;
    }

    private void addUnzipRequest(Map<Format, List<UnzipRequest>> unzipRequests, Application application, UnzipRequest unzipRequest) {
        List<UnzipRequest> requests = unzipRequests.get(application.getCompressFormat());
        if (Objects.isNull(requests)) {
            unzipRequests.put(application.getCompressFormat(), new ArrayList<>());
            requests = unzipRequests.get(application.getCompressFormat());
        }
        requests.add(unzipRequest);
    }

}
