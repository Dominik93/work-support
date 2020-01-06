package com.slusarz.worksupport.filemanager.domain.file;

import lombok.Value;

@Value(staticConstructor = "of")
public class FileName {

    private String name;

}
