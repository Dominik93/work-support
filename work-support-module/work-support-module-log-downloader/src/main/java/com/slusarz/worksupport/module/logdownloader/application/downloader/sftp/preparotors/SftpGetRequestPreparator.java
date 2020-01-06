package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp.preparotors;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpGetRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import com.slusarz.worksupport.module.logdownloader.domain.file.FtpMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SftpGetRequestPreparator {

    public List<SftpGetRequest> prepareSftpGetRequests(DownloadLog downloadLog) {
        List<SftpGetRequest> ftpMetadatas = new ArrayList<>();
        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                FtpMetadata source = createFtpMetadata(application, server);
                ftpMetadatas.add(SftpGetRequest.of(source));
            }
        }
        log.info("Prepared request [" + ftpMetadatas + "]");
        return ftpMetadatas;
    }

    private FtpMetadata createFtpMetadata(Application application, Server server) {
        DirectoryPath remoteArchivePath = DirectoryPathFactory.ofRemoteArchivePath(application, server);
        FileName currentLogFileName = application.getCurrentLogFileName();
        return FtpMetadata.of(currentLogFileName, remoteArchivePath, server);
    }

}
