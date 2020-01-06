package com.slusarz.worksupport.module.logdownloader.configuration.converter;

import com.slusarz.worksupport.module.logdownloader.domain.Tag;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class TagConverter implements Converter<String, Tag> {
    @Override
    public Tag convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Tag.of(source);
    }
}
