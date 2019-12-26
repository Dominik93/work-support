package com.slusarz.worksupport.permission.domain.granterauthority;

import com.slusarz.worksupport.permission.domain.permission.Permission;
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
