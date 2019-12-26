package com.slusarz.worksupport.module.context.configuration;

import com.slusarz.worksupport.module.context.domain.ExternalModule;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class ExternalModuleConverter implements Converter<String, ExternalModule> {
    @Override
    public ExternalModule convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return ExternalModule.of(source);
    }
}
