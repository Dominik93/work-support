package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import lombok.Value;

import java.nio.file.Path;
import java.nio.file.Paths;

@Value(staticConstructor = "of")
public class FtpMetadata {

    private FileName name;

    private DirectoryPath directoryPath;

    private Server server;

    public String getFullPath() {
        return directoryPath.getPath() + "/" + name.getName();
    }

    public Path getPath() {
        return Paths.get(getFullPath());
    }

}
