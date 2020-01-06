package com.slusarz.worksupport.module.logdownloader.domain;

import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import lombok.Value;

@Value(staticConstructor = "of")
public class Server {

    private DirectoryPath directoryPath;

    public String getDir() {
        return directoryPath.getPath();
    }
}
