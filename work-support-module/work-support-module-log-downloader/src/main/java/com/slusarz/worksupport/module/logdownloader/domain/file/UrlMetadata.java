package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import lombok.Value;

import java.net.MalformedURLException;
import java.net.URL;

@Value(staticConstructor = "of")
public class UrlMetadata {

    private FileName name;

    private URL url;

    private Server server;

    public URL toFullURL() throws MalformedURLException {
        return new URL(url.toString() + "/" + name.getName());
    }

    public static UrlMetadata of(FileName fileName, DirectoryPath directoryPath, Server server) {
        try {
            return UrlMetadata.of(fileName, new URL(directoryPath.getPath()), server);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

}
