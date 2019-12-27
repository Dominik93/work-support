package com.slusarz.worksupport.multitenancy.database.configuration.tenant;

import com.slusarz.worksupport.multitenancy.database.extension.TenantProvider;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TenantSupportIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    @Autowired
    private TenantProvider tenantProvider;

    @Override
    public String resolveCurrentTenantIdentifier() {
        return tenantProvider.provide();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
