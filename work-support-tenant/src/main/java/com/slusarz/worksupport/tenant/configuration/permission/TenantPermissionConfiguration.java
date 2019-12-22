package com.slusarz.worksupport.tenant.configuration.permission;

import com.slusarz.worksupport.commontypes.application.environment.EnvironmentProvider;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.permission.application.PermissionsProvider;
import com.slusarz.worksupport.permission.domain.DefaultPermission;
import com.slusarz.worksupport.permission.domain.Permission;
import com.slusarz.worksupport.tenant.application.permission.TenantPermissionsProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class TenantPermissionConfiguration {

    @Value("#{${security.permissions}}")
    private Map<String, String> permissions;

    @Bean
    public PermissionsProvider permissionsProvider(EnvironmentProvider environmentProvider) {
        Map<Environment, List<Permission>> pemissionsByEnvironment = new HashMap<>();

        for (Map.Entry<String, String> entry : permissions.entrySet()) {
            List<Permission> permissions = Arrays.stream(entry.getValue().split(","))
                    .filter(permission -> !permission.isEmpty())
                    .map(DefaultPermission::of).collect(Collectors.toList());

            pemissionsByEnvironment.put(DefaultEnvironment.of(entry.getKey()), permissions);
        }

        log.info("Start permission provider: " + pemissionsByEnvironment);
        return new TenantPermissionsProvider(pemissionsByEnvironment, environmentProvider);
    }

}
