package com.slusarz.worksupport.module.sqlexecutor.application.service;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.sqlexecutor.domain.executablesql.ExecutableSql;
import com.slusarz.worksupport.module.sqlexecutor.domain.metadata.SqlMetadata;
import com.slusarz.worksupport.module.sqlexecutor.domain.result.SqlResult;
import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;
import com.slusarz.worksupport.module.sqlexecutor.security.SqlExecutorAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@SqlExecutorAuthorize
public class SqlService {

    @Autowired
    private SqlRepository sqlRepository;

    @Provide
    private SqlProvider sqlProvider;

    @Transactional
    public SqlResult executeSql(final ExecutableSql executableSql) {
        SqlStatement sqlStatement = sqlProvider.findSqlStatement(executableSql.getSqlName());
        return sqlRepository.executeSqlStatement(sqlStatement, executableSql.getSqlParameters());
    }

    public List<SqlMetadata> getSqlMetadatas() {
        return sqlProvider.provideSqlMetadatas();
    }

}
