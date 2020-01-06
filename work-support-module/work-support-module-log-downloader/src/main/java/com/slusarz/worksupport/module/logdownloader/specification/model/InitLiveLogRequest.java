package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.Data;

import java.util.List;

@Data
public class InitLiveLogRequest {

    private List<String> applications;

    private LogQuery logQuery;

}
