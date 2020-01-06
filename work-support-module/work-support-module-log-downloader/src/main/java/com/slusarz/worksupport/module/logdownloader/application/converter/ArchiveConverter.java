package com.slusarz.worksupport.module.logdownloader.application.converter;

import com.slusarz.worksupport.filemanager.domain.file.File;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import org.springframework.stereotype.Component;

@Component
public class ArchiveConverter {
    public File toFile(Archive file) {
        return File.of(file.getName(), file.getDirectoryPath());
    }
}
