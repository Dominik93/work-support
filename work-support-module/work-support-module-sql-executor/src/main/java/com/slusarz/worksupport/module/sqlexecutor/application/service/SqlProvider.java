package com.slusarz.worksupport.module.sqlexecutor.application.service;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.module.sqlexecutor.configuration.SqlExecutorConfiguration;
import com.slusarz.worksupport.module.sqlexecutor.configuration.config.SqlMetadataConfig;
import com.slusarz.worksupport.module.sqlexecutor.domain.metadata.SqlMetadata;
import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.permission.PermissionNames;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;
import com.slusarz.worksupport.permission.domain.permission.Permission;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import com.slusarz.worksupport.tenant.database.application.context.DatabaseTenantContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Provider
public class SqlProvider {

    @Provide
    private PermissionsProvider permissionsProvider;

    @Autowired
    private SqlExecutorConfiguration sqlExecutorConfiguration;

    @Autowired
    private SqlParameterRetriever sqlParameterRetriever;

    public SqlStatement findSqlStatement(SqlName name) {
        SqlMetadataConfig config = sqlExecutorConfiguration.getMetadatas().stream()
                .filter(sqlMetadataConfig -> sqlMetadataConfig.getName().equals(name))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return SqlStatement.of(config.getSql(), config.getType());
    }

    public List<SqlMetadata> provideSqlMetadatas() {
        List<SqlMetadata> sqlMetadatas = retrieveSqlMetadatas();
        return filterByPermission(sqlMetadatas, PermissionNames.SQL_MODIFICATION_QUERY);
    }

    private List<SqlMetadata> filterByPermission(List<SqlMetadata> sqlMetadatas, Permission permission) {
        boolean hasPermission = !permissionsProvider.hasPermission(permission);
        return sqlMetadatas.stream()
                .filter(sqlMetadata -> hasPermission && !sqlMetadata.getType().isModificationQuery())
                .collect(Collectors.toList());
    }

    private List<SqlMetadata> retrieveSqlMetadatas() {
        return sqlExecutorConfiguration.getMetadatas().stream()
                .filter(sqlConfig -> sqlConfig.getDatabase().equals(DatabaseTenantContext.getCurrentTenant()))
                .map(this::toSqlMetadata)
                .collect(Collectors.toList());
    }

    private SqlMetadata toSqlMetadata(SqlMetadataConfig sqlMetadataConfig) {
        List<SqlParameter> sqlParameters = sqlParameterRetriever.retrieveSqlParameters(sqlMetadataConfig.getSql());
        return SqlMetadata.of(sqlMetadataConfig.getName(),
                sqlMetadataConfig.getDescription(),
                sqlMetadataConfig.getDatabase(),
                sqlMetadataConfig.getType(),
                sqlParameters);
    }

}
