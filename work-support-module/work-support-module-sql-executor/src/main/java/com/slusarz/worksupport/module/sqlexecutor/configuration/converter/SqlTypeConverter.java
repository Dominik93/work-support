package com.slusarz.worksupport.module.sqlexecutor.configuration.converter;

import com.slusarz.worksupport.module.sqlexecutor.domain.type.SqlType;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class SqlTypeConverter implements Converter<String, SqlType> {
    @Override
    public SqlType convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return SqlType.valueOf(source);
    }
}
