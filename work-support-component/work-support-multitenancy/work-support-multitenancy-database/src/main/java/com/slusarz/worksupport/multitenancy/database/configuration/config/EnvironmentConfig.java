package com.slusarz.worksupport.multitenancy.database.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties
public class EnvironmentConfig {

    private Environment environment;

    private List<DataSourceConfig> connections;

}
