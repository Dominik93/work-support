package com.slusarz.worksupport.tenant.database.application.interceptor;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import com.slusarz.worksupport.tenant.database.application.context.DatabaseTenantContext;
import com.slusarz.worksupport.tenant.database.domain.DatabaseTenantFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Component
public class DatabaseTenantInterceptor extends HandlerInterceptorAdapter {

    private DatabaseTenantFactory tenantFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Pre handling the request " + request.getContextPath() + request.getServletPath());
        String tenant = request.getHeader(TenantConstants.TENANT_HEADER_NAME);

        if (Objects.nonNull(tenant)) {
            Database databaseTenant = tenantFactory.create(tenant);
            log.info("Set database tenant to [" + tenant + "]");
            DatabaseTenantContext.setCurrentTenant(databaseTenant);
        } else {
            log.info("Database tenant is absent. Default tenant will be used.");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("After handling the request " + request.getContextPath() + request.getServletPath());
        DatabaseTenantContext.clear();
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("After rendering the view");
        super.afterCompletion(request, response, handler, ex);
    }

}