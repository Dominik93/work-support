package com.slusarz.worksupport.tenant.environment.configuration;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = "com.slusarz.worksupport.tenant.environment")
public class EnvironmentTenantConfiguration {

    @Value("${tenant.environment.default}")
    private Environment environment;

    @PostConstruct
    public void initTenantContext() {
        EnvironmentTenantContext.init(environment);
    }


}
