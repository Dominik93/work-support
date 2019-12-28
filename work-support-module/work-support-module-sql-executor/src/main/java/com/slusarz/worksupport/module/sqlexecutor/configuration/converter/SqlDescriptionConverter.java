package com.slusarz.worksupport.module.sqlexecutor.configuration.converter;

import com.slusarz.worksupport.module.sqlexecutor.domain.description.SqlDescription;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class SqlDescriptionConverter implements Converter<String, SqlDescription> {
    @Override
    public SqlDescription convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return SqlDescription.of(source);
    }
}
