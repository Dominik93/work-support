package com.slusarz.worksupport.filemanager.application.remover;

import com.slusarz.worksupport.filemanager.domain.file.IFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AsyncFileRemoverService implements FileRemover {

    @Autowired
    @Qualifier("fileRemoverService")
    private FileRemover fileRemover;

    @Async
    @Override
    public void deleteFiles(final List<IFile> files) {
        fileRemover.deleteFiles(files);
    }

    @Async
    @Override
    public void deleteFilesCreatedBefore(List<IFile> files, LocalDateTime localDateTime) {
        fileRemover.deleteFilesCreatedBefore(files, localDateTime);
    }

}
