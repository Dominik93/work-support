package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.multitenancy.database.extension.TenantProvider;
import com.slusarz.worksupport.tenant.database.application.context.DatabaseTenantContext;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;

@Provider
public class TestTenantProvider implements TenantProvider {
    @Override
    public String provide() {
        return EnvironmentTenantContext.getCurrentTenant().getName()
                + "-" +
                DatabaseTenantContext.getCurrentTenant().getName();
    }
}
