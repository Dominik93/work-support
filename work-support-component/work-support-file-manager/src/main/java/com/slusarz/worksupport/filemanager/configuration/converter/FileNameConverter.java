package com.slusarz.worksupport.filemanager.configuration.converter;

import com.slusarz.worksupport.filemanager.domain.file.FileName;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class FileNameConverter implements Converter<String, FileName> {
    @Override
    public FileName convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return FileName.of(source);
    }
}
