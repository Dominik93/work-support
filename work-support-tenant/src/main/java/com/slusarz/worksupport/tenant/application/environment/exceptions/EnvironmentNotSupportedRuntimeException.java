package com.slusarz.worksupport.tenant.application.environment.exceptions;

import com.slusarz.worksupport.commontypes.domain.Environment;

import java.util.List;

public class EnvironmentNotSupportedRuntimeException extends RuntimeException {

    public EnvironmentNotSupportedRuntimeException(List<Environment> environments, Environment environment) {
        super("[" + environment + "] not supported. Available environmnets: " + environments);
    }

}
