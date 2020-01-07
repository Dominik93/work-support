package com.slusarz.worksupport.tenant.environment.extension.permission.application;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.permission.configuration.config.PermissionConfig;
import com.slusarz.worksupport.permission.configuration.config.SecurityConfig;
import com.slusarz.worksupport.permission.domain.permission.Permission;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Slf4j
@Provider
public class TenantPermissionsProvider implements PermissionsProvider {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private EnvironmentProvider environmentProvider;

    @Override
    public boolean hasPermission(Permission permission) {
        boolean hasPermission = getAllPermissions().stream().anyMatch(s -> s.equals(permission));
        log.info("Environment [" + environmentProvider.provide() + "] " +
                (hasPermission ? "has" : "not has") +
                " permission [" + permission + "]");
        return hasPermission;
    }

    @Override
    public List<Permission> getAllPermissions() {
        Environment environment = environmentProvider.provide();
        return securityConfig.getPermissions().stream()
                .filter(permissionConfig -> permissionConfig.getEnvironment().equals(environment))
                .findFirst().map(PermissionConfig::getPermissionNames).orElse(Collections.emptyList());
    }
}
