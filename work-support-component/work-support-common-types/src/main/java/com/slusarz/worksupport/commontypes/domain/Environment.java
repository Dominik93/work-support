package com.slusarz.worksupport.commontypes.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Environment {

    private String name;

}
