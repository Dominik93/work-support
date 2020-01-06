package com.slusarz.worksupport.module.logdownloader.application.downloader.sftp;

import com.slusarz.worksupport.commontypes.application.executor.Execute;
import com.slusarz.worksupport.module.logdownloader.domain.CurrentFileLogChannelResult;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpDownloadRequest;
import com.slusarz.worksupport.module.logdownloader.domain.downloader.SftpGetRequest;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.ssh.application.channel.executor.SftpChannelExecutor;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@Component
public class SftpDownloader {

    @Execute
    private SftpChannelExecutor<SftpChannelResult> syncSftpChannelExecutor;

    @Async
    public Future<CurrentLogFile> getAsync(SftpGetRequest sftpGetRequest) {
        SftpLogFileDownloader sftpLogFileDownloader = SftpLogFileDownloader.of(sftpGetRequest);

        CurrentFileLogChannelResult currentFileLogChannelResult
                = (CurrentFileLogChannelResult) syncSftpChannelExecutor.execute(sftpLogFileDownloader);

        return new AsyncResult<>(currentFileLogChannelResult.getCurrentLogFile());
    }


    @Async
    public Future<Void> downloadAsync(SftpDownloadRequest sftpDownloadRequest) {
        SftpArchiveFileDownloader sftpLogFileDownloader = SftpArchiveFileDownloader.of(sftpDownloadRequest);
        syncSftpChannelExecutor.execute(sftpLogFileDownloader);
        return new AsyncResult<>(null);
    }

}
