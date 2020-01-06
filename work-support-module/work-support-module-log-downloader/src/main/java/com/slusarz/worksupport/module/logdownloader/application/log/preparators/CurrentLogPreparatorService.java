package com.slusarz.worksupport.module.logdownloader.application.log.preparators;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.module.logdownloader.application.archive.ArchiveWriterService;
import com.slusarz.worksupport.module.logdownloader.application.file.FileService;
import com.slusarz.worksupport.module.logdownloader.application.file.create.ArchiveCreatorService;
import com.slusarz.worksupport.module.logdownloader.application.file.processor.FilesProcessorService;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.domain.file.BytesToZip;
import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.module.logdownloader.security.LogAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@LogAuthorize
public class CurrentLogPreparatorService implements LogPreparator {

    @Autowired
    private ArchiveWriterService archiveWriterService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ArchiveCreatorService archiveCreatorService;

    @Autowired
    private FilesProcessorService filesProcessorService;

    @Loggable
    @Override
    public Archive prepareLog(final DownloadLog downloadLog) throws IOException {
        List<CurrentLogFile> currentLogFiles = fileService.downloadCurrentFiles(downloadLog);

        if (downloadLog.isProcessNecessary()) {
            filesProcessorService.processCurrentFiles(downloadLog, currentLogFiles);
        }
        Archive archive = archiveCreatorService.createArchive();
        List<BytesToZip> collect = toBytesToZips(currentLogFiles);
        archiveWriterService.writeBytesToZip(archive, collect);
        return archive;
    }

    private List<BytesToZip> toBytesToZips(List<CurrentLogFile> currentLogFiles) {
       return currentLogFiles.stream()
                .map(log -> BytesToZip.of(log.getServerFileName(), log.getBytes()))
                .collect(Collectors.toList());

    }

}
