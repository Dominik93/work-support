package com.slusarz.worksupport.tenant.application.environment;

import com.slusarz.worksupport.commontypes.application.environment.EnvironmentProvider;
import com.slusarz.worksupport.commontypes.domain.Environment;

public class TenantEnvironmentProvider implements EnvironmentProvider {

    @Override
    public Environment provide() {
        return EnvironmentTenantContext.getCurrentTenant();
    }
}
