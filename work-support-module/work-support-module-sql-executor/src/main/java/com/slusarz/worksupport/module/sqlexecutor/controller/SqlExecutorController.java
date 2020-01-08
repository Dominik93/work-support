package com.slusarz.worksupport.module.sqlexecutor.controller;

import com.slusarz.worksupport.module.sqlexecutor.application.service.SqlService;
import com.slusarz.worksupport.module.sqlexecutor.controller.mapper.SqlMapper;
import com.slusarz.worksupport.module.sqlexecutor.domain.metadata.SqlMetadata;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.specification.SqlExecutorApi;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.ExecuteSqlRequest;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.ExecuteSqlResponse;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.GetSqlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SqlExecutorController implements SqlExecutorApi {

    @Autowired
    private SqlMapper sqlMapper;

    @Autowired
    private SqlService sqlService;

    @Override
    public ExecuteSqlResponse executeSqlResponse(@RequestBody ExecuteSqlRequest executeSqlRequest) {
        SqlResult sqlResult = sqlService.executeSql(sqlMapper.toSql(executeSqlRequest));
        return ExecuteSqlResponse.builder()
                .headers(sqlMapper.toHeaders(sqlResult))
                .rows(sqlMapper.toRows(sqlResult))
                .build();
    }

    @Override
    public GetSqlResponse getSql() {
        List<SqlMetadata> executableSql = sqlService.getSqlMetadatas();
        return GetSqlResponse
                .builder()
                .sqlMetadatas(sqlMapper.toSql(executableSql))
                .build();
    }
}
