package com.slusarz.worksupport.filemanager.application.creator;

import com.slusarz.worksupport.filemanager.domain.create.FileToCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileCreatorService {

    public void createFile(FileToCreate file) throws IOException {
        Files.createFile(Paths.get(file.getFileName()));
    }

    public void createFile(File file) throws IOException {
        Files.createFile(file.toPath());
    }

    public void createFile(Path path) throws IOException {
        Files.createFile(path);
    }

}
