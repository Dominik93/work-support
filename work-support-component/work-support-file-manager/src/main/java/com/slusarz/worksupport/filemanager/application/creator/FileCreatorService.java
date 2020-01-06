package com.slusarz.worksupport.filemanager.application.creator;

import com.slusarz.worksupport.filemanager.application.converter.FileConverter;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileCreatorService {

    @Autowired
    private FileConverter fileConverter;

    public void createFile(IFile file) throws IOException {
        log.info("Create file [" + file + "]");
        Files.createFile(fileConverter.toPath(file));
    }

    public void createDirectoryIfNotExist(final DirectoryPath directoryPath) throws IOException {
        if (!this.fileExist(directoryPath)) {
            this.createDirectory(directoryPath);
        }
    }

    public boolean fileExist(DirectoryPath directoryPath) {
        boolean exists = Files.exists(Paths.get(directoryPath.getPath()));
        log.info("File [" + directoryPath + "] " + (exists ? "exists" : "not exists"));
        return exists;
    }

    public void createDirectory(DirectoryPath directoryPath) throws IOException {
        log.info("Create directory [" + directoryPath + "]");
        Files.createDirectories(Paths.get(directoryPath.getPath()));
    }


}
