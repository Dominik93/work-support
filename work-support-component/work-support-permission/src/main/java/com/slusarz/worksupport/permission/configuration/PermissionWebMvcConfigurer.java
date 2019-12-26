package com.slusarz.worksupport.permission.configuration;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.permission.application.interceptor.PermissionSecurityInterceptor;
import com.slusarz.worksupport.permission.domain.authentication.PermissionAuthentication;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PermissionWebMvcConfigurer implements WebMvcConfigurer {

    @Provide
    private PermissionsProvider permissionsProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        PermissionAuthentication authentication = new PermissionAuthentication(permissionsProvider);
        registry.addInterceptor(new PermissionSecurityInterceptor(authentication));
    }

}
