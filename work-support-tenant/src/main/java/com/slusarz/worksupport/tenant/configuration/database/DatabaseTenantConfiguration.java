package com.slusarz.worksupport.tenant.configuration.database;

import com.slusarz.worksupport.commontypes.domain.DefaultDatabase;
import com.slusarz.worksupport.tenant.application.database.DatabaseTenantContext;
import com.slusarz.worksupport.tenant.application.database.interceptor.DatabaseTenantInterceptor;
import com.slusarz.worksupport.tenant.application.database.validator.DatabaseTenantValidator;
import com.slusarz.worksupport.tenant.domain.tenant.DatabaseTenantFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DatabaseTenantConfiguration {

    @Value("${tenant.database.default}")
    private String defaultDatabase;

    @Value("${tenant.database.available}")
    private List<String> databases;

    @PostConstruct
    public void initTenantContext() {
        DatabaseTenantContext.init(DefaultDatabase.of(defaultDatabase));
    }

    @Bean
    public DatabaseTenantValidator databaseTenantValidator() {
        return new DatabaseTenantValidator(databases.stream().map(DefaultDatabase::of).collect(Collectors.toList()));
    }

    @Bean
    public DatabaseTenantFactory databaseTenantFactory(DatabaseTenantValidator databaseTenantValidator) {
        return new DatabaseTenantFactory(databaseTenantValidator);
    }

    @Bean
    public DatabaseTenantInterceptor databaseTenantInterceptor(DatabaseTenantFactory databaseTenantFactory) {
        return new DatabaseTenantInterceptor(databaseTenantFactory);
    }

}
