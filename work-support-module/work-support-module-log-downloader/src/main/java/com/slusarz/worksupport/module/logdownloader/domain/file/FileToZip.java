package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import lombok.Value;

@Value(staticConstructor = "of")
public class FileToZip  implements IFile {

    private DirectoryPath directoryPath;

    private FileName name;

    private Server server;

}
