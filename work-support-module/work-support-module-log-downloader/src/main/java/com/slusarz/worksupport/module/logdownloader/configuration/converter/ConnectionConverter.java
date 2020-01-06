package com.slusarz.worksupport.module.logdownloader.configuration.converter;

import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class ConnectionConverter implements Converter<String, Connection> {
    @Override
    public Connection convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Connection.valueOf(source);
    }
}
