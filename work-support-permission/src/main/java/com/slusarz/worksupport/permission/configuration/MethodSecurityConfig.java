package com.slusarz.worksupport.permission.configuration;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.permission.application.PermissionsProvider;
import com.slusarz.worksupport.permission.application.expression.PermissionMethodSecurityExpressionHandler;
import com.slusarz.worksupport.permission.application.expression.PermissionPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Provide
    private PermissionsProvider permissionsProvider;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        PermissionMethodSecurityExpressionHandler expressionHandler = new PermissionMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new PermissionPermissionEvaluator(permissionsProvider));
        return expressionHandler;
    }
}
