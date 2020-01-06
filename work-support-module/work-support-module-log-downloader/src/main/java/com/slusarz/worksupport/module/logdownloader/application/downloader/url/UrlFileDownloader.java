package com.slusarz.worksupport.module.logdownloader.application.downloader.url;

import com.slusarz.worksupport.component.async.application.FutureAsyncLoop;
import com.slusarz.worksupport.module.logdownloader.application.file.FileDownloader;
import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.UrlDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.domain.file.UrlMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UrlFileDownloader implements FileDownloader {

    @Autowired
    private FutureAsyncLoop<UrlDownloadRequest, Void> voidFutureAsyncLoop;

    @Autowired
    private FutureAsyncLoop<UrlMetadata, CurrentLogFile> futureAsyncLoop;

    @Autowired
    private UrlDownloader urlDownloader;

    @Autowired
    private UrlDownloadRequestPreparator urlDownloadRequestPreparator;


    public void downloadFiles(DownloadLog downloadLog) {
        List<UrlDownloadRequest> urlDownloadRequests = urlDownloadRequestPreparator.prepareUrlDownloadRequests(downloadLog);
        voidFutureAsyncLoop.invokeAsync(urlDownloadRequests, urlDownloader::downloadAsync);
    }

    public List<CurrentLogFile> downloadCurrentFiles(DownloadLog downloadLog) {
        List<UrlMetadata> urlMetadatas = urlDownloadRequestPreparator.prepareUrlMetadatas(downloadLog);
        return futureAsyncLoop.invokeAsync(urlMetadatas, urlDownloader::getAsync);
    }

    @Override
    public Connection getConnection() {
        return Connection.HTTP;
    }


}
