package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import lombok.Value;

@Value(staticConstructor = "of")
public class BytesToZip {

    private FileName name;

    private byte[] bytes;

}
