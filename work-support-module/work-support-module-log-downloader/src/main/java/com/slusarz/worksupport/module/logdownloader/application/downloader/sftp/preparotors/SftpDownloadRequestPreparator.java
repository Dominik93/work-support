package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp.preparotors;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ToString
@Component
public class SftpDownloadRequestPreparator {

    @Provide
    private EnvironmentProvider environmentProvider;

    @FunctionalInterface
    private interface Creator {
        SftpDownloadRequest create(Application application, Server server, LocalDateTime localDateTime);
    }

    @Autowired
    private SftpDownloadRequestCreator sftpDownloadRequestCreator;

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    public List<SftpDownloadRequest> prepareSftpDownloadRequest(DownloadLog downloadLog) {
        List<SftpDownloadRequest> sftpDownloadRequests = new ArrayList<>();
        sftpDownloadRequests.addAll(createSftpDownloadRequests(downloadLog, sftpDownloadRequestCreator::createArchiveSftpDownloadRequest));
        sftpDownloadRequests.addAll(createSftpDownloadRequests(downloadLog, sftpDownloadRequestCreator::createLogSftpDownloadRequest));
        return sftpDownloadRequests;
    }


    private List<SftpDownloadRequest> createSftpDownloadRequests(DownloadLog downloadLog, Creator creator) {
        List<SftpDownloadRequest> sftpDownloadRequests = new ArrayList<>();

        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                for (LocalDateTime localDateTime : downloadLog.getDates()) {
                    if (!logExist(application, server, localDateTime)) {
                        SftpDownloadRequest sftpDownloadRequest = creator.create(application, server, localDateTime);
                        log.info("prepare SftpDownloadRequest " + sftpDownloadRequest);
                        sftpDownloadRequests.add(sftpDownloadRequest);
                    }
                }
            }
        }
        return sftpDownloadRequests;
    }

    private boolean logExist(Application application, Server server, LocalDateTime localDateTime) {
        DirectoryPath localPath = DirectoryPathFactory.ofLocalPath(logDownloaderConfiguration.getLogDirectory(), environmentProvider.provide(), server);
        FileName logFileName = application.getLogFileName(localDateTime);
        FtpMetadata destinationLog = FtpMetadata.of(logFileName, localPath, server);
        boolean exists = Files.exists(destinationLog.getPath());
        log.info("log " + destinationLog.getPath() + " exist" + exists);
        return exists;
    }

}
