package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.module.test.configuration.TestConfiguration;
import com.slusarz.worksupport.module.test.domain.login.Login;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Provider
public class LoginsProvider {

    @Autowired
    private TestConfiguration testConfiguration;

    @Provide
    private EnvironmentProvider tenantEnvironmentProvider;

    private Map<Environment, List<Login>> logins;

    @PostConstruct
    private void init() {
        logins = testConfiguration.getLoginsByEnvironment();
    }

    public List<Login> getLogins() {
        return logins.getOrDefault(tenantEnvironmentProvider.provide(), new ArrayList<>());
    }
}
