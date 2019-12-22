package com.slusarz.worksupport.module.context.configuration;

import com.slusarz.worksupport.module.context.domain.ExternalModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ContextModuleConfiguration {

    @Value("${services}")
    private List<String> services;

    @Bean
    public List<ExternalModule> externalModules() {
        return services.stream().map(ExternalModule::of).collect(Collectors.toList());
    }

}
