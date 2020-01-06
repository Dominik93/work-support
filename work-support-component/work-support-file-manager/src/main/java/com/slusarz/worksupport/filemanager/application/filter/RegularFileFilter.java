package com.slusarz.worksupport.filemanager.application.filter;

import com.slusarz.worksupport.filemanager.application.converter.FileConverter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegularFileFilter {

    @Autowired
    private FileConverter fileConverter;

    public List<Path> filterFiles(List<IFile> files) {
        return files.stream()
                .map(file -> fileConverter.toPath(file))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }


    public List<Path> filterPath(List<Path> files) {
        return files.stream()
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

}

