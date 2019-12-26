package com.slusarz.worksupport.commontypes.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Database {

    private String name;

}