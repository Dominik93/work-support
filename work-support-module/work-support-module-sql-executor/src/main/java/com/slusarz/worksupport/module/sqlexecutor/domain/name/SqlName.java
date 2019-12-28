package com.slusarz.worksupport.module.sqlexecutor.domain.name;

import lombok.Value;

@Value(staticConstructor = "of")
public class SqlName {

    private String name;

}