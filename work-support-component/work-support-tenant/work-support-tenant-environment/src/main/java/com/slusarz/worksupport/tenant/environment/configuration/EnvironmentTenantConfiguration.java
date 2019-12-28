package com.slusarz.worksupport.tenant.environment.configuration;

import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Getter
@Configuration
@ComponentScan(basePackages = "com.slusarz.worksupport.tenant.environment")
public class EnvironmentTenantConfiguration {

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    @PostConstruct
    public void initTenantContext() {
        EnvironmentTenantContext.init(environmentConfiguration.getDefaultEnvironment());
    }


}
