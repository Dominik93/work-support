package com.slusarz.worksupport.filemanager.application.remover;

import com.slusarz.worksupport.filemanager.domain.remove.FileToRemove;

import java.time.LocalDateTime;
import java.util.List;

public interface FileRemover {

    void deleteFiles(List<FileToRemove> files);

    void deleteFilesCreatedBefore(List<FileToRemove> files, LocalDateTime localDateTime);


}
