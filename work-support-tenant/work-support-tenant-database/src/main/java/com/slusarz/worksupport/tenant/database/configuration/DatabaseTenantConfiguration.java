package com.slusarz.worksupport.tenant.database.configuration;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.tenant.database.application.context.DatabaseTenantContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.slusarz.worksupport.tenant.database")
public class DatabaseTenantConfiguration {

    @Value("${tenant.database.default}")
    private String defaultDatabase;

    @PostConstruct
    public void initTenantContext() {
        DatabaseTenantContext.init(Database.of(defaultDatabase));
    }

}
