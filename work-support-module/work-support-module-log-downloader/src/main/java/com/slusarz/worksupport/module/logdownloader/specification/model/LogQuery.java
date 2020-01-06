package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.Data;

import java.util.List;

@Data
public class LogQuery {

    private int bufferSize;

    private boolean onlyException;

    private List<String> queries;

}
