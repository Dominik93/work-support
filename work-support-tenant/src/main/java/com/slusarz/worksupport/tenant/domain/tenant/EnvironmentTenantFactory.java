package com.slusarz.worksupport.tenant.domain.tenant;


import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.application.environment.validator.EnvironmentTenantValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnvironmentTenantFactory {

    private EnvironmentTenantValidator environmentTenantValidator;

    public Environment create(String tenant) {
        DefaultEnvironment defaultEnvironment = DefaultEnvironment.of(tenant.split(TenantConstants.SEPARATOR)[0]);
        environmentTenantValidator.validate(defaultEnvironment);
        return defaultEnvironment;
    }
}
