package com.slusarz.worksupport.module.logdownloader.application.file.processor;

import com.slusarz.worksupport.commontypes.application.CloseableUtil;
import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.module.logdownloader.application.processors.line.SimpleLineProcessor;
import com.slusarz.worksupport.module.logdownloader.application.processors.text.BufferedTextProcessor;
import com.slusarz.worksupport.module.logdownloader.application.processors.text.SimpleTextProcessor;
import com.slusarz.worksupport.module.logdownloader.application.processors.text.TextProcessor;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.domain.file.FileToZip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@Service
public class FileProcessorAsyncService {

    private Map<Integer, TextProcessor> processors = new HashMap<>();

    @PostConstruct
    public void init() {
        processors.put(0, new SimpleTextProcessor(new SimpleLineProcessor()));
    }

    @Async
    @Loggable
    public Future<CurrentLogFile> processCurrentFiles(DownloadLog downloadLog, CurrentLogFile currentLogFile) {
        InputStream is = null;
        StringWriter writer = null;
        BufferedReader reader = null;
        Future<CurrentLogFile> future = null;
        try {
            is = new ByteArrayInputStream(currentLogFile.getBytes());
            reader = new BufferedReader(new InputStreamReader(is));
            writer = new StringWriter();

            getTextProcessor(downloadLog.getBufferSize()).process(writer, reader, downloadLog.toLogQuery());

            future = new AsyncResult<>(
                    CurrentLogFile.of(currentLogFile.getName(), currentLogFile.getServer(), writer.toString().getBytes()));

            CloseableUtil.close(writer, reader, is);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            CloseableUtil.close(writer, reader, is);
        }
        return future;
    }

    @Async
    @Loggable
    public Future<FileToZip> processFiles(DownloadLog downloadLog, FileToZip file) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Future<FileToZip> filesToZip = null;
        try {
            Path path = Paths.get(file.getDirectoryPath().getPath()
                    .replace(file.getName().getName(), "processed_" + file.getName().getName()));
            File inputFile = Paths.get(file.getFullPath().getPath()).toFile();
            File tempFile = path.toFile();

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            getTextProcessor(downloadLog.getBufferSize())
                    .process(writer, reader, downloadLog.toLogQuery());

            CloseableUtil.close(writer, reader);
            filesToZip = new AsyncResult<>(FileToZip.of(file.getDirectoryPath(), file.getName(), file.getServer()));
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            CloseableUtil.close(writer, reader);
        }
        return filesToZip;
    }

    private TextProcessor getTextProcessor(Integer buffer) {
        return processors.getOrDefault(buffer, new BufferedTextProcessor(new SimpleLineProcessor()));
    }

}
