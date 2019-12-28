package com.slusarz.worksupport.module.sqlexecutor.application.service;

import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;

import java.util.List;

public interface SqlRepository {

    SqlResult executeSqlStatement(SqlStatement sqlStatement, List<SqlParameter> sqlParameters);
}
