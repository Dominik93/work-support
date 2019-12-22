package com.slusarz.worksupport.tenant.configuration.environment;

import com.slusarz.worksupport.tenant.application.environment.interceptor.EnvironmentTenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EnvironmentTenantWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private EnvironmentTenantInterceptor environmentTenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(environmentTenantInterceptor);
    }

}
