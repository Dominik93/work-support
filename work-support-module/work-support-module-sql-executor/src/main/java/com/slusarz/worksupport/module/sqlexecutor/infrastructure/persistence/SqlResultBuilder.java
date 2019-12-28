package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence;

import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.row.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SqlResultBuilder {

    public SqlResult.SqlResultBuilder build(List<Map<String, Object>> results) {
        SqlResult.SqlResultBuilder sqlResultBuilder = SqlResult.builder();
        sqlResultBuilder.headers(getHeaders(results));
        sqlResultBuilder.rows(getRows(results));
        return sqlResultBuilder;
    }

    private List<Row> getRows(List<Map<String, Object>> results) {
        List<Row> rows = new ArrayList<>();
        for (Map<String, Object> result : results) {
            Row.RowBuilder builder = Row.builder();
            for (Object value : result.values()) {
                builder.value(Optional.ofNullable(value)
                        .map(Object::toString)
                        .orElse("null"));
            }
            rows.add(builder.build());
        }
        return rows;
    }

    private List<String> getHeaders(List<Map<String, Object>> results) {
        List<String> headers = new ArrayList<>();
        for (Map<String, Object> result : results) {
            for (String column : result.keySet()) {
                if (!headers.contains(column)) {
                    headers.add(column);
                }
            }
        }
        return headers;
    }

}
