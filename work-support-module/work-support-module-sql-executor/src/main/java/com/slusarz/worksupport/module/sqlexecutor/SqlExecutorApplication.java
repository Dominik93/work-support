package com.slusarz.worksupport.module.sqlexecutor;

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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableSwagger
@EnableEurekaClient
@EnableEnvironmentTenant
@EnablePermissionSecurity
@EnableDatabaseTenant
@EnableMultitenantDatabase
@EnableEnvironmentTenantPermissionExtension
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.sqlexecutor")
public class SqlExecutorApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SqlExecutorApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.headers().frameOptions().sameOrigin();
        security.httpBasic().disable();
        security.authorizeRequests().anyRequest().permitAll();
        security.csrf().disable(); // TODO FIXME
    }

}