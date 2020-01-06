package com.slusarz.worksupport.module.logdownloader.application.log.preparators;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.filemanager.application.filter.ExistingFileFilter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.module.logdownloader.application.archive.ArchiveWriterService;
import com.slusarz.worksupport.module.logdownloader.application.file.FileService;
import com.slusarz.worksupport.module.logdownloader.application.file.collector.FilesCollectorService;
import com.slusarz.worksupport.module.logdownloader.application.file.create.ArchiveCreatorService;
import com.slusarz.worksupport.module.logdownloader.application.file.processor.FilesProcessorService;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.domain.file.FileToZip;
import com.slusarz.worksupport.module.logdownloader.security.LogAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@LogAuthorize
public class LogPreparatorService implements LogPreparator {

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @Autowired
    private ArchiveWriterService archiveWriterService;

    @Autowired
    private ArchiveCreatorService archiveCreatorService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FilesCollectorService filesCollectorService;

    @Autowired
    private FilesProcessorService filesProcessorService;

    @Autowired
    private ExistingFileFilter existingFileFilter;

    @Loggable
    public Archive prepareLog(final DownloadLog downloadLog) throws IOException {
        fileService.downloadFiles(downloadLog);

        List<FileToZip> files = getLogs(downloadLog);

        log.info("Files [" + files + "]");
        Archive archive = archiveCreatorService.createArchive();
        archiveWriterService.writeFilesToZip(archive, files);
        return archive;
    }

    private List<FileToZip> getLogs(DownloadLog downloadLog) {
        List<FileToZip> fileToZips = filesCollectorService.collectFiles(downloadLog);
        List<FileToZip> files = (List<FileToZip>) (List<?>) existingFileFilter.filter((List<IFile>) (List<?>) fileToZips);
        if (downloadLog.isProcessNecessary()) {
            files = filesProcessorService.processFiles(downloadLog, files);
        }
        return files;
    }

}
