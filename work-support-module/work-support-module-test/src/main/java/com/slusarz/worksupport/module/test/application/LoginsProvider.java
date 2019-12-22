package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.module.test.domain.login.Login;
import com.slusarz.worksupport.tenant.application.environment.TenantEnvironmentProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Provider
public class LoginsProvider {

    @Autowired
    private Map<Environment, List<Login>> logins;

    @Provide
    private TenantEnvironmentProvider tenantEnvironmentProvider;

    public List<Login> getLogins() {
        return logins.getOrDefault(tenantEnvironmentProvider.provide(), new ArrayList<>());
    }
}
