package com.slusarz.worksupport.multitenancy.database.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Database;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
public class DataSourceConfig  {

    private Database database;

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String schema;

}
