package com.slusarz.worksupport.module.logdownloader.domain.file;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class LogQuery {

    private List<String> queries;

    private boolean onlyException;

    private int bufferSize;

}
