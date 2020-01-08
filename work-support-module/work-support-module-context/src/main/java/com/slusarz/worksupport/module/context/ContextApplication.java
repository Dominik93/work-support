package com.slusarz.worksupport.module.context;

import com.slusarz.worksupport.component.security.configuration.EnableSecurity;
import com.slusarz.worksupport.swagger.EnableSwagger;
import com.slusarz.worksupport.tenant.database.configuration.EnableDatabaseTenant;
import com.slusarz.worksupport.tenant.environment.configuration.EnableEnvironmentTenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableSwagger
@EnableSecurity
@EnableEurekaClient
@EnableDatabaseTenant
@EnableEnvironmentTenant
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.context")
public class ContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContextApplication.class, args);
    }

}