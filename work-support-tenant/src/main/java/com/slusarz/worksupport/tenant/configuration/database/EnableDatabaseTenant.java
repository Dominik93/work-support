package com.slusarz.worksupport.tenant.configuration.database;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DatabaseTenantConfiguration.class, DatabaseTenantWebMvcConfigurer.class})
public @interface EnableDatabaseTenant {
}
