package com.slusarz.worksupport.filemanager.domain.file;

import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.filemanager.domain.path.FullPath;

public interface IFile {

    FileName getName();

    DirectoryPath getDirectoryPath();

    default FullPath getFullPath() {
        return FullPath.of(getDirectoryPath().getPath() + "/" + getName().getName());
    }
}
