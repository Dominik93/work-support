package com.slusarz.worksupport.module.logdownloader.domain.application;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class ApplicationFileName {

    private String name;

}
