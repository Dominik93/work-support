package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import lombok.Value;

@Value(staticConstructor = "of")
public class CurrentLogFile {

    private FileName name;

    private Server server;

    private byte[] bytes;

    /**
     * @return corpo_svr1_fileName
     */
    public FileName getServerFileName() {
        return FileName.of(getServer().getDir() + "_" + getName().getName());
    }

}
