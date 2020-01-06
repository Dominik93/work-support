package com.slusarz.worksupport.module.logdownloader.domain;

import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class LiveLog {

    private List<Application> applications;

    private List<String> queries;

    private boolean onlyException;

    private int bufferSize;


}
