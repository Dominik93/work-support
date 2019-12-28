package com.slusarz.worksupport.module.sqlexecutor.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.module.sqlexecutor.domain.description.SqlDescription;
import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import com.slusarz.worksupport.module.sqlexecutor.domain.type.SqlType;
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
public class SqlMetadataConfig {

    private SqlName name;

    private Database database;

    private SqlDescription description;

    private SqlType type;

    private String sql;

}
