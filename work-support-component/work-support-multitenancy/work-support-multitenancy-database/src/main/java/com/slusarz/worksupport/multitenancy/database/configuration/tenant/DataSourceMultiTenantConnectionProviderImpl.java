package com.slusarz.worksupport.multitenancy.database.configuration.tenant;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.multitenancy.database.configuration.datasource.DataSourceProvider;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;

@Provider
public class DataSourceMultiTenantConnectionProviderImpl
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    @Provide
    private DataSourceProvider dataSourceProvider;

    @Override
    protected DataSource selectAnyDataSource() {
        return this.dataSourceProvider.any();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return this.dataSourceProvider.getDataSource(tenantIdentifier);

    }

}
