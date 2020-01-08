package com.slusarz.worksupport.module.sqlexecutor;

import com.slusarz.worksupport.component.security.configuration.EnableSecurity;
import com.slusarz.worksupport.multitenancy.database.configuration.EnableMultitenantDatabase;
import com.slusarz.worksupport.permission.configuration.EnablePermissionSecurity;
import com.slusarz.worksupport.swagger.EnableSwagger;
import com.slusarz.worksupport.tenant.database.configuration.EnableDatabaseTenant;
import com.slusarz.worksupport.tenant.environment.configuration.EnableEnvironmentTenant;
import com.slusarz.worksupport.tenant.environment.extension.permission.configuration.EnableEnvironmentTenantPermissionExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableSwagger
@EnableSecurity
@EnableEurekaClient
@EnableEnvironmentTenant
@EnablePermissionSecurity
@EnableDatabaseTenant
@EnableMultitenantDatabase
@EnableEnvironmentTenantPermissionExtension
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.sqlexecutor")
public class SqlExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlExecutorApplication.class, args);
    }
}