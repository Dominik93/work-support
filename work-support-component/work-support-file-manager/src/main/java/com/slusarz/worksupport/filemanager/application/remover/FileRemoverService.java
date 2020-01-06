package com.slusarz.worksupport.filemanager.application.remover;

import com.slusarz.worksupport.filemanager.application.converter.FileConverter;
import com.slusarz.worksupport.filemanager.application.filter.FileOlderThanFilter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
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

    @Autowired
    private FileConverter fileConverter;

    @Override
    public void deleteFilesCreatedBefore(List<IFile> files, LocalDateTime localDateTime) {
        List<IFile> fileToRemoves = fileOlderThanFilter.filter(files, localDateTime);
        deleteFiles(fileToRemoves);
    }

    @Override
    public void deleteFiles(final List<IFile> files) {
        files.forEach(this::deleteFile);
    }

    private void deleteFile(IFile file) {
        try {
            log.info("Delete file [" + file + "]");
            Files.delete(fileConverter.toPath(file));
        } catch (IOException e) {
            log.info("Can`t delete file [" + file + "]. " + e.getMessage());
        }
    }

}
