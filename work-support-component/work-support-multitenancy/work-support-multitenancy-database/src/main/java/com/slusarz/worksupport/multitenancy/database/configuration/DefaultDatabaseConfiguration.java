package com.slusarz.worksupport.multitenancy.database.configuration;

import com.slusarz.worksupport.multitenancy.database.configuration.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultDatabaseConfiguration {

    @Value("${database.default.driver-class-name:#{null}}")
    private String driverClassName;

    @Value("${database.default.username:#{null}}")
    private String username;

    @Value("${database.default.password:#{null}}")
    private String password;

    public DataSourceConfig getDataSourceConfig() {
        return DataSourceConfig.builder()
                .driverClassName(driverClassName)
                .password(password)
                .username(username)
                .build();
    }

}
