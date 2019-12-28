package com.slusarz.worksupport.module.sqlexecutor.domain.description;

import lombok.Value;

@Value(staticConstructor = "of")
public class SqlDescription {

    private String description;

}