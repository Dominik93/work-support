package com.slusarz.worksupport.filemanager.application.filter;

import com.slusarz.worksupport.filemanager.application.converter.FileConverter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExistingFileFilter {

    @Autowired
    private FileConverter fileConverter;

    public List<IFile> filter(List<IFile> files) {
        return files.stream()
                .filter(file -> Files.exists(fileConverter.toPath(file)))
                .collect(Collectors.toList());
    }

}
