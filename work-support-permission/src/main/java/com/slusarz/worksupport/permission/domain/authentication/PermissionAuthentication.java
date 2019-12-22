package com.slusarz.worksupport.permission.domain.authentication;

import com.slusarz.worksupport.permission.application.PermissionsProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PermissionAuthentication implements Authentication {

    private PermissionsProvider permissionsProvider;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionsProvider.getAllPermissions().stream()
                .map(PermissionGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }
}
