package com.slusarz.worksupport.module.logdownloader.application.processors.text;

import com.slusarz.worksupport.module.logdownloader.application.processors.line.LineProcessor;
import com.slusarz.worksupport.module.logdownloader.domain.Buffer;
import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@Slf4j
@AllArgsConstructor
public class BufferedTextProcessor implements TextProcessor {

    private LineProcessor lineProcessor;

    public void process(Writer writer, BufferedReader reader, LogQuery logQuery) throws IOException {
        Buffer buffer = new Buffer(logQuery.getBufferSize());
        Buffer suffixBuffer = new Buffer(logQuery.getBufferSize());
        boolean addToSuffixBuffer = false;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            log.debug("process line: " + currentLine);
            if (addToSuffixBuffer) {
                log.debug("line is added to suffix buffer");
                suffixBuffer.add(currentLine);
            }
            if (lineProcessor.process(currentLine, logQuery)) {
                log.debug("line contain query and is exception");
                writeBuffer(writer, buffer);
                writeLine(writer, currentLine);
                addToSuffixBuffer = true;
            } else {
                log.debug("line is added to buffer");
                buffer.add(currentLine);
                if (suffixBuffer.flushBuffer()) {
                    log.debug("suffix buffer need flush");
                    writeBuffer(writer, suffixBuffer);
                    addToSuffixBuffer = false;
                }
            }
        }
        if (addToSuffixBuffer) {
            writeBuffer(writer, suffixBuffer);
        }
    }

    private void writeLine(Writer writer, String line) throws IOException {
        writer.write(line + System.getProperty("line.separator"));
    }

    private void writeBuffer(Writer writer, Buffer buffer) throws IOException {
        for (String line : buffer.pop()) {
            writeLine(writer, line);
        }
    }
}
