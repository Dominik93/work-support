package com.slusarz.worksupport.tenant.environment.extension.permission.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnvironmentTenantPermissionConfiguration.class)
public @interface EnableEnvironmentTenantPermissionExtension {
}
