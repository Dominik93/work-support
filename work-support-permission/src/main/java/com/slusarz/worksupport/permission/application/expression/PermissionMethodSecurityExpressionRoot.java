package com.slusarz.worksupport.permission.application.expression;

import com.slusarz.worksupport.permission.domain.DefaultPermission;
import com.slusarz.worksupport.permission.domain.authentication.PermissionGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Slf4j
public class PermissionMethodSecurityExpressionRoot
        extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    public PermissionMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasPermission(String permission) {
        boolean hasPermission = hasPermission(new PermissionGrantedAuthority(DefaultPermission.of(permission)));
        if (hasPermission) {
            log.info("Has permission " + permission);
        } else {
            log.info("Has not permission " + permission + "!");
        }
        return hasPermission;
    }

    private boolean hasPermission(GrantedAuthority grantedAuthority) {
        return getAuthentication().getAuthorities().contains(grantedAuthority);
    }

    @Override
    public void setFilterObject(Object o) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object o) {

    }

    @Override
    public Object getReturnObject() {
        return null;
    }

    @Override
    public Object getThis() {
        return this;
    }
}