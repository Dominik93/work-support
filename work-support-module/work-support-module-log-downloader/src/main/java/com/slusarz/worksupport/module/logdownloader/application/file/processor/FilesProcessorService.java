package com.slusarz.worksupport.module.logdownloader.application.file.processor;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.component.async.application.FutureAsyncLoop;
import com.slusarz.worksupport.component.async.application.FuturesCollector;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.domain.file.FileToZip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
public class FilesProcessorService {

    @Autowired
    private FileProcessorAsyncService fileProcessorAsyncService;

    @Autowired
    private FutureAsyncLoop<DownloadLog, CurrentLogFile> asyncLoop;

    @Autowired
    private FuturesCollector futuresCollector;

    @Loggable
    public void processCurrentFiles(DownloadLog downloadLog, List<CurrentLogFile> currentLogFiles) {
        List<Future<CurrentLogFile>> processedFiles = new ArrayList<>();
        for (CurrentLogFile currentLogFile : currentLogFiles) {
            processedFiles.add(fileProcessorAsyncService.processCurrentFiles(downloadLog, currentLogFile));
        }
        currentLogFiles.clear();
        currentLogFiles.addAll(futuresCollector.collectFutures(processedFiles));
    }

    @Loggable
    public List<FileToZip> processFiles(DownloadLog downloadLog, List<FileToZip> files) {
        List<Future<FileToZip>> paths = new ArrayList<>();
        for (FileToZip file : files) {
            paths.add(fileProcessorAsyncService.processFiles(downloadLog, file));
        }
        return futuresCollector.collectFutures(paths);
    }

}
