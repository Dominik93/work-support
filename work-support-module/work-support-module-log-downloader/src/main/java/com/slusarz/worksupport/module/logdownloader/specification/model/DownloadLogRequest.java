package com.slusarz.worksupport.module.logdownloader.specification.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DownloadLogRequest {

    private List<String> applications;

    @JsonFormat(pattern = "M/d/yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "H:mm")
    private LocalTime from;

    @JsonFormat(pattern = "H:mm")
    private LocalTime to;

    private LogQuery logQuery;

    private boolean current;

}
