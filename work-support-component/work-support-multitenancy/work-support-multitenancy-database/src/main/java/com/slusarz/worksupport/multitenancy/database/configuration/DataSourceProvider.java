package com.slusarz.worksupport.multitenancy.database.configuration;

import com.slusarz.worksupport.commontypes.application.DefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.MultipleDefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.multitenancy.database.configuration.config.DataSourceConfig;
import com.slusarz.worksupport.multitenancy.database.configuration.config.EnvironmentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Provider
public class DataSourceProvider {

    private Map<String, DataSource> dataSources;

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private DefaultDatabaseConfiguration defaultDatabaseConfiguration;

    @PostConstruct
    public void dataSources() {
        Map<String, DataSource> result = new HashMap<>();
        Map<Database, DataSourceConfig> defaultDatabaseConfigs
                = databaseConfiguration.getDefaultConnections().stream()
                .collect(Collectors.toMap(DataSourceConfig::getDatabase, Function.identity()));

        for (EnvironmentConfig environment : databaseConfiguration.getEnvironments()) {
            for (DataSourceConfig connection : environment.getConnections()) {

                DataSourceConfig dataSourceConfig = defaultDatabaseConfigs.get(connection.getDatabase());

                DefaultPropertiesHelper<DataSourceConfig, String> helper
                        = new MultipleDefaultPropertiesHelper<>(connection, Arrays.asList(defaultDatabaseConfiguration.getDataSourceConfig(), dataSourceConfig));

                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName(helper.getOrDefault(DataSourceConfig::getDriverClassName));
                dataSource.setUrl(helper.getOrDefault(DataSourceConfig::getUrl));
               // dataSource.setSchema(helper.getOrDefault(DataSourceConfig::getSchema));
                dataSource.setUsername(helper.getOrDefault(DataSourceConfig::getUsername));
                dataSource.setPassword(helper.getOrDefault(DataSourceConfig::getPassword));
                log.info("create datasource: " +
                        dataSource.getUsername() + ":" +
                        dataSource.getPassword() + ":" +
                        dataSource.getSchema() + ":" +
                        dataSource.getUrl());

                result.put(environment.getEnvironment().getName() + "-" + connection.getDatabase().getName(), dataSource);
            }
        }
        dataSources = result;
    }

    public DataSource getDataSource(String tenant) {
        return dataSources.get(tenant);
    }

    public DataSource any() {
        return dataSources.values().iterator().next();
    }
}
