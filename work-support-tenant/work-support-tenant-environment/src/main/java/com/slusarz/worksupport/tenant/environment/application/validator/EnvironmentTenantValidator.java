package com.slusarz.worksupport.tenant.environment.application.validator;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.environment.application.exceptions.EnvironmentNotSupportedRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnvironmentTenantValidator {

    @Value("${tenant.environment.any:false}")
    private boolean anyEnvironment;

    @Value("${tenant.environment.available:}#{T(java.util.Collections).emptyList()}")
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
        return anyEnvironment;
    }

}
