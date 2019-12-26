package com.slusarz.worksupport.permission.extension;

import com.slusarz.worksupport.permission.domain.permission.Permission;

import java.util.List;

public interface PermissionsProvider {

    boolean hasPermission(Permission permission);

    List<Permission> getAllPermissions();

}
