package com.slusarz.worksupport.tenant.configuration.database;

import com.slusarz.worksupport.tenant.application.database.interceptor.DatabaseTenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DatabaseTenantWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private DatabaseTenantInterceptor databaseTenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(databaseTenantInterceptor);
    }

}
