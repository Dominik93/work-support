package com.slusarz.worksupport.module.logdownloader.application.processors.text;

import com.slusarz.worksupport.module.logdownloader.application.processors.line.LineProcessor;
import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@Slf4j
@AllArgsConstructor
public class SimpleTextProcessor implements TextProcessor {

    private LineProcessor lineProcessor;

    public void process(Writer writer, BufferedReader reader, LogQuery logQuery) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            log.debug("process line: " + currentLine);

            if (lineProcessor.process(currentLine, logQuery)) {
                log.debug("line contain query and is exception");
                writeLine(writer, currentLine);
            }
        }
    }

    private void writeLine(Writer writer, String line) throws IOException {
        writer.write(line + System.getProperty("line.separator"));
    }

}
