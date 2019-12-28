package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.repository;

import com.slusarz.worksupport.module.sqlexecutor.application.service.SqlRepository;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;
import com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.executor.ExecutorManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SqlJpaRepository implements SqlRepository {

    @Autowired
    private ExecutorManager executorManager;

    @Override
    public SqlResult executeSqlStatement(SqlStatement sqlStatement, List<SqlParameter> sqlParameters) {
        log.info(sqlStatement + " " + sqlParameters);
        SqlResult.SqlResultBuilder sqlResultBuilder;
        sqlResultBuilder = executorManager.get(sqlStatement.getType()).executeStatement(sqlStatement, sqlParameters);
        return sqlResultBuilder.success(true).build();
    }

}
