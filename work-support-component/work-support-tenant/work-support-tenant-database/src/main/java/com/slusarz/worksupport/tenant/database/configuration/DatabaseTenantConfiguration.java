package com.slusarz.worksupport.tenant.database.configuration;

import com.slusarz.worksupport.tenant.database.application.context.DatabaseTenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.slusarz.worksupport.tenant.database")
public class DatabaseTenantConfiguration {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @PostConstruct
    public void initTenantContext() {
        DatabaseTenantContext.init(databaseConfiguration.getDefaultDatabase());
    }

}
