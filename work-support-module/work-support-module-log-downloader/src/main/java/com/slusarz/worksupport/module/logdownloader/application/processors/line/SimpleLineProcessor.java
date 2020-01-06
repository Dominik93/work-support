package com.slusarz.worksupport.module.logdownloader.application.processors.line;

import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SimpleLineProcessor implements LineProcessor {

    private final Pattern DATE_PATTERN = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})");

    public boolean process(String currentLine, LogQuery logQuery) {
        boolean exception = isException(currentLine, logQuery);
        boolean containsQuery = containsQuery(currentLine, logQuery.getQueries());
        log.info("contain query " + containsQuery + " is exception " + exception);
        return exception && containsQuery;
    }

    private boolean isException(String line, LogQuery logQuery) {
        if (!logQuery.isOnlyException()) {
            return true;
        }
        Matcher matcher = DATE_PATTERN.matcher(line);
        return !matcher.lookingAt();
    }

    private boolean containsQuery(String line, List<String> queries) {
        if (queries.isEmpty()) {
            return true;
        }
        for (String query : queries) {
            if (line.contains(query)) {
                return true;
            }
        }
        return false;
    }

}
