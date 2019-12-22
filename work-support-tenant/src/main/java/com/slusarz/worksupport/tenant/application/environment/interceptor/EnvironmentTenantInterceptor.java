package com.slusarz.worksupport.tenant.application.environment.interceptor;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.tenant.application.environment.EnvironmentTenantContext;
import com.slusarz.worksupport.tenant.domain.tenant.EnvironmentTenantFactory;
import com.slusarz.worksupport.tenant.domain.tenant.TenantConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class EnvironmentTenantInterceptor extends HandlerInterceptorAdapter {

    private EnvironmentTenantFactory tenantFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Pre handling the request " + request.getContextPath() + request.getServletPath());
        String tenant = request.getHeader(TenantConstants.TENANT_HEADER_NAME);

        if (Objects.nonNull(tenant)) {
            Environment environmentTenant = tenantFactory.create(tenant);
            log.info("Set environment tenant to [" + tenant + "]");
            EnvironmentTenantContext.setCurrentTenant(environmentTenant);
        } else {
            log.info("Environment tenant is absent. Default tenant will be used.");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("After handling the request " + request.getContextPath() + request.getServletPath());
        EnvironmentTenantContext.clear();
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("After rendering the view");
        super.afterCompletion(request, response, handler, ex);
    }

}
