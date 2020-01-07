package com.slusarz.worksupport.module.logdownloader.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
@ConfigurationProperties("log-downloader")
public class ApplicationsConfiguration {

    private List<ApplicationConfig> applications;

    private List<EnvironmentConfig> environments;

    public EnvironmentConfig getEnvironment(Environment environment) {
        return environments.stream()
                .collect(Collectors.toMap(EnvironmentConfig::getEnvironment, Function.identity()))
                .get(environment);
    }

}