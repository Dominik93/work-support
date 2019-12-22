package com.slusarz.worksupport.permission.application.expression;

import com.slusarz.worksupport.permission.application.PermissionsProvider;
import com.slusarz.worksupport.permission.domain.Permission;
import lombok.AllArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

@AllArgsConstructor
public class PermissionPermissionEvaluator implements PermissionEvaluator {

    private PermissionsProvider permissionsProvider;

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPermission(() -> permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPermission(() -> permission.toString().toUpperCase());
    }

    private boolean hasPermission(Permission permission) {
        return permissionsProvider.hasPermission(permission);
    }

}