package com.slusarz.worksupport.module.logdownloader.application.file.create;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.application.creator.FileCreatorService;
import com.slusarz.worksupport.module.logdownloader.application.converter.ArchiveConverter;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.exceptions.LogModuleRuntimeException;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ArchiveCreatorService {

    @Provide
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @Autowired
    private FileCreatorService fileCreatorService;

    @Provide
    private EnvironmentProvider environmentProvider;

    @Autowired
    private ArchiveConverter archiveConverter;

    public Archive createArchive() {
        try {
            Archive file = Archive.of(logDownloaderConfiguration, environmentProvider.provide());
            log.info("Create zip [" + file + "]");
            fileCreatorService.createFile(archiveConverter.toFile(file));
            return file;
        } catch (IOException e) {
            throw new LogModuleRuntimeException(e);
        }
    }

}
