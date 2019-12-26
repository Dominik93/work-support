package com.slusarz.worksupport.tenant.environment.domain;


import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import com.slusarz.worksupport.tenant.environment.application.validator.EnvironmentTenantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentTenantFactory {

    @Autowired
    private EnvironmentTenantValidator environmentTenantValidator;

    public Environment create(String tenant) {
        Environment environment = Environment.of(tenant.split(TenantConstants.SEPARATOR)[0]);
        environmentTenantValidator.validate(environment);
        return environment;
    }
}
