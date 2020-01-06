package com.slusarz.worksupport.filemanager.application.remover;

import com.slusarz.worksupport.filemanager.domain.file.IFile;

import java.time.LocalDateTime;
import java.util.List;

public interface FileRemover {

    void deleteFiles(List<IFile> files);

    void deleteFilesCreatedBefore(List<IFile> files, LocalDateTime localDateTime);


}
