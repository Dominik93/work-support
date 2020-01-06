package com.slusarz.worksupport.module.logdownloader.application.file;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.module.logdownloader.application.archive.ArchiveService;
import com.slusarz.worksupport.module.logdownloader.application.downloader.DownloaderManager;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileService {

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private DownloaderManager downloaderManager;

    @Loggable
    public List<CurrentLogFile> downloadCurrentFiles(final DownloadLog downloadLog) {
        return downloaderManager.get().downloadCurrentFiles(downloadLog);
    }

    @Loggable
    public void downloadFiles(final DownloadLog downloadLog) {
        downloaderManager.get().downloadFiles(downloadLog);
        archiveService.unpack(downloadLog);
    }
}
