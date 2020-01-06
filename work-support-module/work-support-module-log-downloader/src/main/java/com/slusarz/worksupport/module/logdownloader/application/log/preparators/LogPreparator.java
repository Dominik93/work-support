package com.slusarz.worksupport.module.logdownloader.application.log.preparators;

import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;

import java.io.IOException;

public interface LogPreparator {

    Archive prepareLog(final DownloadLog downloadLog) throws IOException;

}
