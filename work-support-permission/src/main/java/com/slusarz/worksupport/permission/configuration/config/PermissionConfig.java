package com.slusarz.worksupport.permission.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.permission.domain.permission.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties
public class PermissionConfig {

    private Environment environment;

    private List<Permission> permissionNames;

}
