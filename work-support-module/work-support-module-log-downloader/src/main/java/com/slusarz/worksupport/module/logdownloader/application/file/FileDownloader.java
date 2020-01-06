package com.slusarz.worksupport.module.logdownloader.application.file;

import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;

import java.util.List;

public interface FileDownloader {

    void downloadFiles(DownloadLog downloadLog);

    List<CurrentLogFile> downloadCurrentFiles(DownloadLog downloadLog);

    Connection getConnection();
}
