package com.slusarz.worksupport.tenant.application.permission;

import com.slusarz.worksupport.commontypes.application.environment.EnvironmentProvider;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.permission.application.PermissionsProvider;
import com.slusarz.worksupport.permission.domain.Permission;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class TenantPermissionsProvider implements PermissionsProvider {

    private Map<Environment, List<Permission>> permissions;

    private EnvironmentProvider environmentProvider;

    @Override
    public boolean hasPermission(Permission permission) {
        return permissions.get(DefaultEnvironment.of(environmentProvider.provide().getName()))
                .stream()
                .anyMatch(perm -> perm.getName().equals(permission.getName()));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissions.get(DefaultEnvironment.of(environmentProvider.provide().getName()));
    }
}
