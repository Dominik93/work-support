package com.slusarz.worksupport.filemanager.domain.filename;

import lombok.Value;

@Value(staticConstructor = "of")
public class FileName {

    private String name;

}
