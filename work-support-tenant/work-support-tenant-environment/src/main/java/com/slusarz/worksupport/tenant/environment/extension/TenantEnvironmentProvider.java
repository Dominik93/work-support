package com.slusarz.worksupport.tenant.environment.extension;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class TenantEnvironmentProvider implements EnvironmentProvider {

    @Override
    public Environment provide() {
        return EnvironmentTenantContext.getCurrentTenant();
    }
}
