package com.slusarz.worksupport.permission.domain;

import lombok.Value;


@Value(staticConstructor = "of")
public class DefaultPermission implements Permission {

    private String name;

}
