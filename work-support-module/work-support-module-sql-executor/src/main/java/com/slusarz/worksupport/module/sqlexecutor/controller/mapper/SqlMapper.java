package com.slusarz.worksupport.module.sqlexecutor.controller.mapper;

import com.slusarz.worksupport.module.sqlexecutor.domain.executablesql.ExecutableSql;
import com.slusarz.worksupport.module.sqlexecutor.domain.metadata.SqlMetadata;
import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.ExecuteSqlRequest;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.Row;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SqlMapper {

    public List<com.slusarz.worksupport.module.sqlexecutor.specification.model.SqlMetadata> toSql(List<SqlMetadata> sqlMetadatas) {
        return sqlMetadatas.stream()
                .map(this::toSql)
                .collect(Collectors.toList());
    }

    private com.slusarz.worksupport.module.sqlexecutor.specification.model.SqlMetadata toSql(SqlMetadata sqlMetadata) {
        return com.slusarz.worksupport.module.sqlexecutor.specification.model.SqlMetadata.builder()
                .name(sqlMetadata.getSqlName().getName())
                .description(sqlMetadata.getSqlDescription().getDescription())
                .parameters(sqlMetadata.getSqlParameters()
                        .stream()
                        .map(sqlParameter -> com.slusarz.worksupport.module.sqlexecutor.specification.model.SqlParameter
                                .builder()
                                .name(sqlParameter.getName())
                                .value(sqlParameter.getOption().getValue())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


    public List<String> toHeaders(SqlResult sqlResult) {
        return sqlResult.getHeaders();
    }

    public List<Row> toRows(SqlResult sqlResult) {
        return sqlResult.getRows().stream()
                .map(row -> Row.builder().values(row.getValues()).build())
                .collect(Collectors.toList());
    }

    public ExecutableSql toSql(ExecuteSqlRequest executeSqlRequest) {
        SqlName sqlName = SqlName.of(executeSqlRequest.getSql().getName());
        List<SqlParameter> sqlParameters = executeSqlRequest.getSql().getParameters()
                .stream()
                .map(sqlParameter -> SqlParameter.of(sqlParameter.getName(), sqlParameter.getValue()))
                .collect(Collectors.toList());
        return ExecutableSql.of(sqlName, sqlParameters);
    }
}
