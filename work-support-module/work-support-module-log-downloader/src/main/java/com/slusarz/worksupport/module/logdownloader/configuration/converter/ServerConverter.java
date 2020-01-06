package com.slusarz.worksupport.module.logdownloader.configuration.converter;

import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationPropertiesBinding
public class ServerConverter implements Converter<String, Server> {
    @Override
    public Server convert(String source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return Server.of(DirectoryPath.of(source));
    }
}
