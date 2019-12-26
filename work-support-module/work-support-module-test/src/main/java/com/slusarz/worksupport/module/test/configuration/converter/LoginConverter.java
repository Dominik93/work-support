package com.slusarz.worksupport.module.test.configuration.converter;

import com.slusarz.worksupport.module.test.domain.login.Login;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class LoginConverter implements Converter<String, Login> {
    @Override
    public Login convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Login.of(source);
    }
}
