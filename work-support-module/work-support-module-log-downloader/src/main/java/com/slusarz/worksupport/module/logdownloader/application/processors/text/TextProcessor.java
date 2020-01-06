package com.slusarz.worksupport.module.logdownloader.application.processors.text;

import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public interface TextProcessor {

    void process(Writer writer, BufferedReader reader, LogQuery logQuery) throws IOException;

}
