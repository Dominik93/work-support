package com.slusarz.worksupport.module.test.configuration;

import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.test.domain.login.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties
public class TestModuleConfiguration {

    @Value("#{${test.logins}}")
    private Map<String, String> logins;

    @Bean
    public Map<Environment, List<Login>> testLogins() {
        Map<Environment, List<Login>> loginsByEnvironment = new HashMap<>();
        for (Map.Entry<String, String> entry : logins.entrySet()) {
            List<Login> logins
                    = Arrays.stream(entry.getValue().split(","))
                    .map(Login::of)
                    .collect(Collectors.toList());
            loginsByEnvironment.put(DefaultEnvironment.of(entry.getKey()), logins);
        }
        return loginsByEnvironment;
    }

}
