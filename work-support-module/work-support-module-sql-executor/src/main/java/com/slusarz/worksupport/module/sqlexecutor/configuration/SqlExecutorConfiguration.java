package com.slusarz.worksupport.module.sqlexecutor.configuration;

import com.slusarz.worksupport.module.sqlexecutor.configuration.config.SqlMetadataConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sql")
public class SqlExecutorConfiguration {

    private List<SqlMetadataConfig> metadatas;

}
