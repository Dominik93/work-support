package com.slusarz.worksupport.tenant.configuration.environment;

import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.tenant.application.environment.EnvironmentTenantContext;
import com.slusarz.worksupport.tenant.application.environment.interceptor.EnvironmentTenantInterceptor;
import com.slusarz.worksupport.tenant.application.environment.validator.EnvironmentTenantValidator;
import com.slusarz.worksupport.tenant.application.environment.TenantEnvironmentProvider;
import com.slusarz.worksupport.tenant.domain.tenant.EnvironmentTenantFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class EnvironmentTenantConfiguration {

    @Value("${tenant.environment.default}")
    private String defaultEnvironment;

    @Value("${tenant.environment.available}")
    private List<String> environments;

    @PostConstruct
    public void initTenantContext() {
        EnvironmentTenantContext.init(DefaultEnvironment.of(defaultEnvironment));
    }

    @Bean
    public EnvironmentTenantValidator EnvironmentTenantValidator() {
        return new EnvironmentTenantValidator(environments.stream().map(DefaultEnvironment::of).collect(Collectors.toList()));
    }

    @Bean
    public EnvironmentTenantFactory EnvironmentTenantFactory(EnvironmentTenantValidator environmentTenantValidator) {
        return new EnvironmentTenantFactory(environmentTenantValidator);
    }

    @Bean
    public EnvironmentTenantInterceptor EnvironmentTenantInterceptor(EnvironmentTenantFactory environmentTenantFactory) {
        return new EnvironmentTenantInterceptor(environmentTenantFactory);
    }

    @Bean
    public TenantEnvironmentProvider tenantEnvironmentProvider() {
        return new TenantEnvironmentProvider();
    }


}
