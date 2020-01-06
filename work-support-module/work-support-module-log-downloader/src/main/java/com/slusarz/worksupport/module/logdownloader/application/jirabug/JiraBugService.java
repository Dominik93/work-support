package com.slusarz.worksupport.module.logdownloader.application.jirabug;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.filemanager.application.creator.FileCreatorService;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.module.logdownloader.application.archive.ArchiveWriterService;
import com.slusarz.worksupport.module.logdownloader.application.converter.ArchiveConverter;
import com.slusarz.worksupport.module.logdownloader.application.file.create.ArchiveCreatorService;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.domain.file.BytesToZip;
import com.slusarz.worksupport.module.logdownloader.security.JiraBugAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@JiraBugAuthorize
public class JiraBugService {

    @Autowired
    private ArchiveWriterService archiveWriterService;

    @Autowired
    private ArchiveCreatorService archiveCreatorService;

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @Autowired
    private FileCreatorService fileCreatorService;

    @Autowired
    private ArchiveConverter archiveConverter;

    @Loggable
    public Archive createJiraBug(Map<Application, String> liveLog) {
        log.info("Create Jira bug [" + liveLog + "]");
        Archive file = Archive.of(logDownloaderConfiguration);
        try {
            fileCreatorService.createFile(archiveConverter.toFile(file));
            archiveWriterService.writeBytesToZip(file, toBytes(liveLog));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private List<BytesToZip> toBytes(Map<Application, String> liveLog) {
        List<BytesToZip> bytesToZips = new ArrayList<>();
        for (Map.Entry<Application, String> entry : liveLog.entrySet()) {
            bytesToZips.add(BytesToZip.of(FileName.of(entry.getKey().getApplicationName().getName() + ".log"), entry.getValue().getBytes()));
        }
        return bytesToZips;
    }

}
