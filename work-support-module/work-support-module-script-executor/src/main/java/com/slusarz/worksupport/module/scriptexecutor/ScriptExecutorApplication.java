package com.slusarz.worksupport.module.scriptexecutor;

import com.slusarz.worksupport.filemanager.configuration.EnableFileManager;
import com.slusarz.worksupport.permission.configuration.EnablePermissionSecurity;
import com.slusarz.worksupport.ssh.configuration.EnableSsh;
import com.slusarz.worksupport.swagger.EnableSwagger;
import com.slusarz.worksupport.tenant.environment.configuration.EnableEnvironmentTenant;
import com.slusarz.worksupport.tenant.environment.extension.permission.configuration.EnableEnvironmentTenantPermissionExtension;
import com.slusarz.worksupport.tenant.environment.extension.ssh.configuration.EnableEnvironmentTenantSshExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableSsh
@EnableAsync
@EnableSwagger
@EnableFileManager
@EnableEurekaClient
@EnableEnvironmentTenant
@EnablePermissionSecurity
@EnableEnvironmentTenantSshExtension
@EnableEnvironmentTenantPermissionExtension
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.scriptexecutor")
public class ScriptExecutorApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ScriptExecutorApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.headers().frameOptions().sameOrigin();
        security.httpBasic().disable();
        security.authorizeRequests().anyRequest().permitAll();
        security.csrf().disable(); // TODO FIXME
    }
}
