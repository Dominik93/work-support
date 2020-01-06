package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp;

import com.slusarz.worksupport.component.async.application.FutureAsyncLoop;
import com.slusarz.worksupport.module.logdownloader.application.downloader.sftp.preparotors.SftpDownloadRequestPreparator;
import com.slusarz.worksupport.module.logdownloader.application.downloader.sftp.preparotors.SftpGetRequestPreparator;
import com.slusarz.worksupport.module.logdownloader.application.file.FileDownloader;
import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpGetRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SftpFileDownloader implements FileDownloader {

    @Autowired
    private SftpDownloader sftpDownloader;

    @Autowired
    private SftpGetRequestPreparator sftpGetRequestPreparator;

    @Autowired
    private SftpDownloadRequestPreparator sftpDownloadRequestPreparator;

    @Autowired
    private FutureAsyncLoop<SftpGetRequest, CurrentLogFile> futureAsyncLoop;

    @Autowired
    private FutureAsyncLoop<SftpDownloadRequest, Void> voidFutureAsyncLoop;

    public void downloadFiles(DownloadLog downloadLog) {
        List<SftpDownloadRequest> sftpDownloadRequests = sftpDownloadRequestPreparator.prepareSftpDownloadRequest(downloadLog);
        voidFutureAsyncLoop.invokeAsync(sftpDownloadRequests, sftpDownloader::downloadAsync);
    }

    public List<CurrentLogFile> downloadCurrentFiles(DownloadLog downloadLog) {
        List<SftpGetRequest> sftpGetRequests = sftpGetRequestPreparator.prepareSftpGetRequests(downloadLog);
        return futureAsyncLoop.invokeAsync(sftpGetRequests, sftpDownloader::getAsync);
    }

    @Override
    public Connection getConnection() {
        return Connection.SSH;
    }


}
