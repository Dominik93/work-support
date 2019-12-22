package com.slusarz.worksupport.module.context.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class ExternalModule {

    private String name;

}
