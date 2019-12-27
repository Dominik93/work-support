package com.slusarz.worksupport.multitenancy.database.configuration;

import com.slusarz.worksupport.multitenancy.database.configuration.config.DataSourceConfig;
import com.slusarz.worksupport.multitenancy.database.configuration.config.EnvironmentConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties("database")
public class DatabaseConfiguration {

    private List<EnvironmentConfig> environments;

    private List<DataSourceConfig> defaultConnections;

}
