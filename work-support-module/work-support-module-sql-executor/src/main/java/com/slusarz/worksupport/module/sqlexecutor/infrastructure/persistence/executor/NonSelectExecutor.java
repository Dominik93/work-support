package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.executor;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;
import com.slusarz.worksupport.multitenancy.database.configuration.datasource.DataSourceProvider;
import com.slusarz.worksupport.multitenancy.database.extension.TenantProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Component
public class NonSelectExecutor {

    @Provide
    private DataSourceProvider dataSourceProvider;

    @Provide
    private TenantProvider tenantProvider;

    public SqlResult.SqlResultBuilder executeNonSelect(SqlStatement sqlStatement, List<SqlParameter> sqlParameters) {
        run(sqlParameters, sqlStatement);
        return SqlResult.builder();
    }

    public void run(List<SqlParameter> sqlParameters, SqlStatement sqlStatement) {
        DataSource dataSource = dataSourceProvider.getDataSource(tenantProvider.provide());
        if (sqlParameters.isEmpty()) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute(sqlStatement.getStatement());
        } else {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
            jdbcTemplate.execute(sqlStatement.getStatement(), createMapSqlParameterSource(sqlParameters), null);
        }
    }

    private MapSqlParameterSource createMapSqlParameterSource(final List<SqlParameter> sqlParameters) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        for (SqlParameter sqlParameter : sqlParameters) {
            mapSqlParameterSource.addValue(sqlParameter.getName(), sqlParameter.getOption().getJdbcValue());
        }
        return mapSqlParameterSource;
    }


}
