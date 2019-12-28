package com.slusarz.worksupport.multitenancy.database.configuration.multitenant;

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
@ConfigurationProperties("multitenant-database")
public class MultitenantDatabaseConfiguration {

    private List<EnvironmentConfig> environments;

    private List<DataSourceConfig> defaultConnections;

}
