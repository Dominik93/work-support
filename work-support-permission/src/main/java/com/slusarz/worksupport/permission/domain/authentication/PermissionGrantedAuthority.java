package com.slusarz.worksupport.permission.domain.authentication;

import com.slusarz.worksupport.permission.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PermissionGrantedAuthority implements GrantedAuthority {

    private Permission permission;

    @Override
    public String getAuthority() {
        return permission.getName();
    }
}
