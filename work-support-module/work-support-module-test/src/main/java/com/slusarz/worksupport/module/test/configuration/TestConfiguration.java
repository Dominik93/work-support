package com.slusarz.worksupport.module.test.configuration;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.test.configuration.config.LoginConfig;
import com.slusarz.worksupport.module.test.domain.login.Login;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "test")
public class TestConfiguration {

    private List<LoginConfig> logins;

    public Map<Environment, List<Login>> getLoginsByEnvironment() {
        return logins.stream().collect(Collectors.toMap(LoginConfig::getEnvironment, LoginConfig::getNames));
    }

}
