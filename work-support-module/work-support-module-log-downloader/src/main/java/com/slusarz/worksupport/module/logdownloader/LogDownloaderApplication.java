package com.slusarz.worksupport.module.logdownloader;

import com.slusarz.worksupport.component.async.configuration.EnableAsync;
import com.slusarz.worksupport.component.security.configuration.EnableSecurity;
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
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableSsh
@EnableAsync
@EnableSwagger
@EnableSecurity
@EnableScheduling
@EnableFileManager
@EnableEurekaClient
@EnableEnvironmentTenant
@EnablePermissionSecurity
@EnableEnvironmentTenantSshExtension
@EnableEnvironmentTenantPermissionExtension
@SpringBootApplication(scanBasePackages = "com.slusarz.worksupport.module.logdownloader")
public class LogDownloaderApplication  {

    public static void main(String[] args) {
        SpringApplication.run(LogDownloaderApplication.class, args);
    }

}
