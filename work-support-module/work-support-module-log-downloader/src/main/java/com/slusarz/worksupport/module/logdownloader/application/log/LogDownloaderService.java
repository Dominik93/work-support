package com.slusarz.worksupport.module.logdownloader.application.log;

import com.slusarz.worksupport.commontypes.application.loggable.Loggable;
import com.slusarz.worksupport.module.logdownloader.application.converter.ArchiveConverter;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.security.LogAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@LogAuthorize
public class LogDownloaderService {

    @Autowired
    private ArchiveConverter archiveConverter;

    @Loggable
    public File downloadLog(final Archive archive) {
        return new File(archive.getFullPath().getPath());
    }

}
