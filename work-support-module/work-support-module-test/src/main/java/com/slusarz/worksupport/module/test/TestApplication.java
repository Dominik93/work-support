package com.slusarz.worksupport.module.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.multitenancy.database.configuration.EnableMultitenantDatabase;
import com.slusarz.worksupport.permission.configuration.EnablePermissionSecurity;
import com.slusarz.worksupport.ssh.configuration.EnableSsh;
import com.slusarz.worksupport.swagger.EnableSwagger;
import com.slusarz.worksupport.tenant.database.configuration.EnableDatabaseTenant;
import com.slusarz.worksupport.tenant.environment.configuration.EnableEnvironmentTenant;
import com.slusarz.worksupport.tenant.environment.extension.permission.configuration.EnableEnvironmentTenantPermissionExtension;
import com.slusarz.worksupport.tenant.environment.extension.ssh.configuration.EnableEnvironmentTenantSshExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.IOException;

@Slf4j
@EnableSsh
@EnableSwagger
@EnableEurekaClient
@EnableEnvironmentTenant
@EnablePermissionSecurity
@EnableDatabaseTenant
@EnableMultitenantDatabase
@EnableEnvironmentTenantSshExtension
@EnableEnvironmentTenantPermissionExtension
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.test")
public class TestApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.headers().frameOptions().sameOrigin();
        security.httpBasic().disable();
        security.authorizeRequests().anyRequest().permitAll();
        security.csrf().disable(); // TODO FIXME
    }

    @Bean
    public String init(ObjectMapper objectMapper) throws IOException {

        ModuleInit moduleInit = ModuleInit.empty();
        String s = objectMapper.writeValueAsString(moduleInit);
        log.info(s);

        ModuleInit moduleInit1 = objectMapper.readValue(s, ModuleInit.class);
log.info(moduleInit1.toString());

        return "";
    }

}