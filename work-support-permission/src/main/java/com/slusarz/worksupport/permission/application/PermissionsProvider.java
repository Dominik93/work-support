package com.slusarz.worksupport.permission.application;

import com.slusarz.worksupport.permission.domain.Permission;

import java.util.List;

public interface PermissionsProvider {

    boolean hasPermission(Permission permission);

    List<Permission> getAllPermissions();

}
