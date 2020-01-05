package com.slusarz.worksupport.filemanager.application.remover;

import com.slusarz.worksupport.filemanager.application.filter.FileOlderThanFilter;
import com.slusarz.worksupport.filemanager.domain.remove.FileToRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FileRemoverService implements FileRemover {

    @Autowired
    private FileOlderThanFilter fileOlderThanFilter;

    @Override
    public void deleteFilesCreatedBefore(List<FileToRemove> files, LocalDateTime localDateTime) {
        List<FileToRemove> fileToRemoves = fileOlderThanFilter.filter(files, localDateTime);
        deleteFiles(fileToRemoves);
    }

    @Override
    public void deleteFiles(final List<FileToRemove> files) {
        for (FileToRemove file : files) {
            deleteFile(file);
        }
    }

    private void deleteFile(FileToRemove file) {
        log.info("Delete file " + file);
        try {
            Files.delete(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Cant delete file " + file);
        }
    }

}
