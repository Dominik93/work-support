package com.slusarz.worksupport.filemanager.domain.directorypath;

import lombok.Value;

@Value(staticConstructor = "of")
public class DirectoryPath {

    private String path;

    private static DirectoryPath of(final String... elements) {
        return DirectoryPath.of(String.join("/", elements));
    }
}
