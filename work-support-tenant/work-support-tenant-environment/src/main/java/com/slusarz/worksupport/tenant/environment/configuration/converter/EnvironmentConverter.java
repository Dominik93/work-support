package com.slusarz.worksupport.tenant.environment.configuration.converter;

import com.slusarz.worksupport.commontypes.domain.Environment;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class EnvironmentConverter implements Converter<String, Environment> {
    @Override
    public Environment convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Environment.of(source);
    }
}
