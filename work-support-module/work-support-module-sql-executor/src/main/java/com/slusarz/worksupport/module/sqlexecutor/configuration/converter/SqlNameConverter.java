package com.slusarz.worksupport.module.sqlexecutor.configuration.converter;

import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class SqlNameConverter implements Converter<String, SqlName> {
    @Override
    public SqlName convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return SqlName.of(source);
    }
}
