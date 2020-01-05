package com.slusarz.worksupport.filemanager.domain.create;

import lombok.Value;

@Value(staticConstructor = "of")
public class FileToCreate {

    private String fileName;
}
