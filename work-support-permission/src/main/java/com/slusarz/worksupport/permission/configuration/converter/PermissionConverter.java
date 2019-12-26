package com.slusarz.worksupport.permission.configuration.converter;

import com.slusarz.worksupport.permission.domain.permission.Permission;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class PermissionConverter implements Converter<String, Permission> {
    @Override
    public Permission convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Permission.of(source);
    }
}
