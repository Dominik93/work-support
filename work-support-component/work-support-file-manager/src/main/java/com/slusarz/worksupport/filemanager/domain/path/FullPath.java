package com.slusarz.worksupport.filemanager.domain.path;

import lombok.Value;

@Value(staticConstructor = "of")
public class FullPath {

    private String path;

}
