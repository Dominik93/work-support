package com.slusarz.worksupport.tenant.database.configuration.converter;

import com.slusarz.worksupport.commontypes.domain.Database;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class DatabaseConverter implements Converter<String, Database> {
    @Override
    public Database convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Database.of(source);
    }
}
