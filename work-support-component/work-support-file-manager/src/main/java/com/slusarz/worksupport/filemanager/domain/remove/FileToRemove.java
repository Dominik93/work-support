package com.slusarz.worksupport.filemanager.domain.remove;

import com.slusarz.worksupport.filemanager.domain.directorypath.DirectoryPath;
import com.slusarz.worksupport.filemanager.domain.filename.FileName;
import lombok.Value;

import java.nio.file.Path;
import java.nio.file.Paths;

@Value(staticConstructor = "of")
public class FileToRemove {

    private FileName name;

    private DirectoryPath directoryPath;

    public String getFullPath() {
        return directoryPath.getPath() + "/" + name.getName();
    }

    public Path getPath() {
        return Paths.get(getFullPath());
    }

}
