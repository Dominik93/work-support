package com.slusarz.worksupport.module.context;

import com.slusarz.worksupport.swagger.EnableSwagger;
import com.slusarz.worksupport.tenant.environment.configuration.EnableEnvironmentTenant;
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
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.context")
public class ContextApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ContextApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
    }
}