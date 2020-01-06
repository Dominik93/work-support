package com.slusarz.worksupport.module.logdownloader.domain.application;

import lombok.Value;

@Value(staticConstructor = "of")
public class ApplicationPath {

    private String path;

}
