package com.slusarz.worksupport.module.logdownloader.domain;

import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.file.LogQuery;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Value(staticConstructor = "of")
public class DownloadLog {

    private List<Application> applications;

    private LocalDate date;

    private LocalTime from;

    private LocalTime to;

    private boolean onlyException;

    private int bufferSize;

    private List<String> queries;

    public LogQuery toLogQuery() {
        return LogQuery.of(getQueries(), isOnlyException(), getBufferSize());
    }

    public boolean isProcessNecessary() {
        return !queries.isEmpty() || onlyException;
    }

    public List<LocalDateTime> getDates() {
        List<LocalDateTime> hours = new ArrayList<>();
        for (long i = 0; i <= ChronoUnit.HOURS.between(from, to); i++) {
            hours.add(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), from.plusHours(i).getHour(), 0));
        }
        return hours;
    }

}
