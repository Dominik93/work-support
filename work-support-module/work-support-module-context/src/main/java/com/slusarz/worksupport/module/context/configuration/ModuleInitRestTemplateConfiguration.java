package com.slusarz.worksupport.module.context.configuration;

import com.slusarz.worksupport.module.context.application.moduleinit.ModuleInitTenantHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ModuleInitRestTemplateConfiguration {

    @Autowired
    private ModuleInitTenantHeaderInterceptor moduleInitTenantHeaderInterceptor;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(moduleInitTenantHeaderInterceptor);
        return restTemplate;
    }

}
