package com.slusarz.worksupport.module.scriptexecutor.specification.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Script {

    private String name;

    private List<Option> options;

    private boolean monitoring;

}
