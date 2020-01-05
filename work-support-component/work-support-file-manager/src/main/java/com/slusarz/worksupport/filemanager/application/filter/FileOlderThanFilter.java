package com.slusarz.worksupport.filemanager.application.filter;

import com.slusarz.worksupport.filemanager.domain.remove.FileToRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileOlderThanFilter {

    public List<FileToRemove> filter(List<FileToRemove> files, LocalDateTime localDateTime) {
        return files.stream()
                .filter(fileToRemove -> filterOldFiles(fileToRemove, localDateTime))
                .collect(Collectors.toList());
    }

    private boolean filterOldFiles(FileToRemove fileToRemove, LocalDateTime localDateTime) {
        try {
            Path path = Paths.get(fileToRemove.getFullPath());
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return (attr.creationTime().toInstant().isBefore(localDateTime.toInstant(ZoneOffset.UTC)));
        } catch (IOException e) {
            e.printStackTrace();
            log.info("File dont exist " + fileToRemove);
            return false;
        }
    }

}
