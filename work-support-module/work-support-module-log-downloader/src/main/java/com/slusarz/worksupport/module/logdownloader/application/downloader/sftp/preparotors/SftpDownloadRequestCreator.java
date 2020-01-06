package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp.preparotors;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SftpDownloadRequestCreator {

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    public SftpDownloadRequest createArchiveSftpDownloadRequest(Application application, Server server, LocalDateTime localDateTime) {
        DirectoryPath localPath = DirectoryPathFactory.ofLocalPath(logDownloaderConfiguration.getLogDirectory(), EnvironmentTenantContext.getCurrentTenant(), server);

        FileName logFileName = application.getLogFileName(localDateTime);
        DirectoryPath remoteArchivePath = DirectoryPathFactory.ofRemoteArchivePath(application, server);

        FtpMetadata destinationLog = FtpMetadata.of(logFileName, localPath, server);
        FtpMetadata sourceLog = FtpMetadata.of(logFileName, remoteArchivePath, server);
        return SftpDownloadRequest.of(sourceLog, destinationLog);
    }

    public SftpDownloadRequest createLogSftpDownloadRequest(Application application, Server server, LocalDateTime localDateTime) {
        FileName archiveFileName = application.getArchiveFileName(localDateTime);
        DirectoryPath localPath = DirectoryPathFactory.ofLocalPath(logDownloaderConfiguration.getLogDirectory(), EnvironmentTenantContext.getCurrentTenant(), server);
        DirectoryPath remoteArchivePath = DirectoryPathFactory.ofRemoteArchivePath(application, server);
        FtpMetadata destinationArchive = FtpMetadata.of(archiveFileName, localPath, server);
        FtpMetadata sourceArchive = FtpMetadata.of(archiveFileName, remoteArchivePath, server);
        return SftpDownloadRequest.of(sourceArchive, destinationArchive);
    }

}
