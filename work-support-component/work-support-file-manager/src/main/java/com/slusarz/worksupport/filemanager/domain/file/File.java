package com.slusarz.worksupport.filemanager.domain.file;

import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import lombok.Value;

@Value(staticConstructor = "of")
public class File implements IFile{

    private FileName name;

    private DirectoryPath directoryPath;

}
