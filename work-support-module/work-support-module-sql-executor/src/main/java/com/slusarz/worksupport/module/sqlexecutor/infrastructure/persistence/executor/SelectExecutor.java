package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.executor;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;
import com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.SqlResultBuilder;
import com.slusarz.worksupport.multitenancy.database.configuration.datasource.DataSourceProvider;
import com.slusarz.worksupport.multitenancy.database.extension.TenantProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SelectExecutor {

    @Provide
    private DataSourceProvider datasourceProvider;

    @Provide
    private TenantProvider tenantProvider;

    public SqlResult.SqlResultBuilder executeSelect(SqlStatement sqlStatement, List<SqlParameter> sqlParameters) {
        List<Map<String, Object>> results = run(sqlParameters, sqlStatement);
        return new SqlResultBuilder().build(results);
    }

    public List<Map<String, Object>> run(List<SqlParameter> sqlParameters, SqlStatement sqlStatement) {
        DataSource dataSource = datasourceProvider.getDataSource(tenantProvider.provide());
        if (sqlParameters.isEmpty()) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.queryForList(sqlStatement.getStatement());
        } else {
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
            return jdbcTemplate.queryForList(sqlStatement.getStatement(), createMapSqlParameterSource(sqlParameters));
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
