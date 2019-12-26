package com.slusarz.worksupport.module.context.application.moduleinit;

import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@NoArgsConstructor
public class ModuleInitTenantHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().set(TenantConstants.TENANT_HEADER_NAME, EnvironmentTenantContext.getCurrentTenant().getName());
        return clientHttpRequestExecution.execute(httpRequest, bytes);

    }

}
