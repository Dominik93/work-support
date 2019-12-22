package com.slusarz.worksupport.commontypes.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class DefaultDatabase implements Database {

    private String name;

}
