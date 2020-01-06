package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import lombok.Value;

@Value(staticConstructor = "of")
public class DirectoryPathFactory {

    private String path;

    public static DirectoryPath ofLocalPath(DirectoryPath logDirectory, Environment environment, Server server) {
        return DirectoryPathFactory.of(logDirectory.getPath(), environment.getName(), server.getDir());
    }

    public static DirectoryPath ofRemoteArchivePath(Application application, Server server) {
        return DirectoryPathFactory.of(application.getApplicationPath().getPath(), server.getDir());
    }

    public static DirectoryPath of(final String... elements) {
        return DirectoryPath.of(String.join("/", elements));
    }
}
