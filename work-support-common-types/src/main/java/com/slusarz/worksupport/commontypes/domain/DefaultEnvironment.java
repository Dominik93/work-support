package com.slusarz.worksupport.commontypes.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class DefaultEnvironment implements Environment {

    private String name;

}
