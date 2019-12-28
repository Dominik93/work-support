package com.slusarz.worksupport.tenant.environment.application.validator;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.environment.application.exceptions.EnvironmentNotSupportedRuntimeException;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentTenantValidator {

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    public void validate(Environment environment) {
        if (!(anyEnvironment() || isAvailable(environment))) {
            throw new EnvironmentNotSupportedRuntimeException(environmentConfiguration.getAvailableEnvironments(), environment);
        }
    }

    private boolean isAvailable(Environment environment){
       return environmentConfiguration.getAvailableEnvironments().contains(environment);
    }

    private boolean anyEnvironment() {
        return environmentConfiguration.isAnyEnvironment();
    }

}
