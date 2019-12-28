package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.executor;

import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;

import java.util.List;

@FunctionalInterface
public interface SqlExecutor {
    SqlResult.SqlResultBuilder executeStatement(SqlStatement sqlStatement, List<SqlParameter> sqlParameters);
}