package com.slusarz.worksupport.module.logdownloader.application.processors.line;

import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;

public interface LineProcessor {

    boolean process(String currentLine, LogQuery logQuery);

}
