package com.slusarz.worksupport.filemanager.application.filter;

import com.slusarz.worksupport.filemanager.application.converter.FileConverter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileOlderThanFilter {

    @Autowired
    private FileConverter fileConverter;

    public List<IFile> filter(List<IFile> files, LocalDateTime localDateTime) {
        return files.stream()
                .filter(fileToRemove -> filterOldFiles(fileToRemove, localDateTime))
                .collect(Collectors.toList());
    }

    private boolean filterOldFiles(IFile file, LocalDateTime localDateTime) {
        try {
            Path path = fileConverter.toPath(file);
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return (attr.creationTime().toInstant().isBefore(localDateTime.toInstant(ZoneOffset.UTC)));
        } catch (IOException e) {
            log.info("File [" + file + "] don`t exist");
            return false;
        }
    }

}
