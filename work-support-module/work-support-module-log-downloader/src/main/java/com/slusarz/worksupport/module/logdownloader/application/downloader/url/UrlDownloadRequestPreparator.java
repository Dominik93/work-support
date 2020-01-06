package com.slusarz.worksupport.module.logdownloader.application.downloader.url;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.UrlDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.module.logdownloader.domain.file.UrlMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UrlDownloadRequestPreparator {

    @Provide
    private EnvironmentProvider environmentProvider;

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    public List<UrlDownloadRequest> prepareUrlDownloadRequests(DownloadLog downloadLog) {
        List<UrlDownloadRequest> urlDownloadRequests = new ArrayList<>();
        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                for (LocalDateTime localDateTime : downloadLog.getDates()) {
                    FileName archiveFileName = application.getArchiveFileName(localDateTime);
                    DirectoryPath localPath
                            = DirectoryPathFactory.ofLocalPath(logDownloaderConfiguration.getLogDirectory(), environmentProvider.provide(), server);
                    FtpMetadata destinationArchive = FtpMetadata.of(archiveFileName, localPath, server);
                    DirectoryPath remoteArchivePath = DirectoryPathFactory.ofRemoteArchivePath(application, server);
                    UrlMetadata sourceArchive = UrlMetadata.of(archiveFileName, remoteArchivePath, server);
                    if (!Files.exists(destinationArchive.getPath())) {
                        UrlDownloadRequest urlDownloadRequest = UrlDownloadRequest.of(sourceArchive, destinationArchive);
                        log.info("Prepare url download request [" + urlDownloadRequest + "]");
                        urlDownloadRequests.add(urlDownloadRequest);
                    }
                }
            }
        }
        return urlDownloadRequests;
    }

    public List<UrlMetadata> prepareUrlMetadatas(DownloadLog downloadLog) {
        List<UrlMetadata> urlMetadatas = new ArrayList<>();
        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                FileName currentLogFileName = application.getCurrentLogFileName();
                DirectoryPath remoteArchivePath = DirectoryPathFactory.ofRemoteArchivePath(application, server);
                UrlMetadata sourceArchive = UrlMetadata.of(currentLogFileName, remoteArchivePath, server);
                urlMetadatas.add(sourceArchive);
            }
        }
        return urlMetadatas;
    }

}
