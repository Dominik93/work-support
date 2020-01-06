package com.slusarz.worksupport.module.logdownloader.domain.application;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.Tag;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Value(staticConstructor = "of")
public class Application {

    private ApplicationName applicationName;

    private ApplicationFileName applicationFileName;

    private ApplicationPath applicationPath;

    private Tag tag;

    private List<Server> servers;

    private DateTimeFormatter dateTimeFormatter;

    private Format logFormat;

    private Format compressFormat;

    private ConcatenationCharacter concatenationCharacter;

    public FileName getCurrentLogFileName() {
        return FileName.of(getApplicationFileName().getName() +
                getLogFormat().getFormat());
    }

    public FileName getLogFileName(final LocalDateTime localDateTime) {
        return FileName.of(getApplicationFileName().getName() +
                getLogFormat().getFormat() +
                getConcatenationCharacter().getCharacter() +
                localDateTime.format(getDateTimeFormatter()));
    }

    public FileName getArchiveFileName(final LocalDateTime localDateTime) {
        return FileName.of(getApplicationFileName().getName() +
                getLogFormat().getFormat() +
                getConcatenationCharacter().getCharacter() +
                localDateTime.format(getDateTimeFormatter()) +
                getCompressFormat().getFormat());
    }

}
