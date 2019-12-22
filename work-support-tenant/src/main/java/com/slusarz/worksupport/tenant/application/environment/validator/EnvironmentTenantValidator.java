package com.slusarz.worksupport.tenant.application.environment.validator;

import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.application.environment.exceptions.EnvironmentNotSupportedRuntimeException;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class EnvironmentTenantValidator {

    private List<Environment> availableEnvironments;

    public void validate(Environment environment) {
        if (!(anyEnvironment() || isAvailable(environment))) {
            throw new EnvironmentNotSupportedRuntimeException(availableEnvironments, environment);
        }
    }

    private boolean isAvailable(Environment environment){
       return availableEnvironments.contains(environment);
    }

    private boolean anyEnvironment() {
        return availableEnvironments.contains(DefaultEnvironment.of("ANY"));
    }

}
