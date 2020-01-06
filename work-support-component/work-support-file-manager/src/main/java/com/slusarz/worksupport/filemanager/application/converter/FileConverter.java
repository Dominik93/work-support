package com.slusarz.worksupport.filemanager.application.converter;

import com.slusarz.worksupport.filemanager.domain.file.IFile;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileConverter {

    public Path toPath(IFile file) {
        return Paths.get(file.getFullPath().getPath());
    }

}
