package com.slusarz.worksupport.module.test.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.test.domain.login.Login;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties
public class LoginConfig {

    private Environment environment;

    private List<Login> names;


}
