package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence.executor;

import com.slusarz.worksupport.module.sqlexecutor.domain.type.SqlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExecutorManager {

    @Autowired
    private NonSelectExecutor nonSelectExecutor;

    @Autowired
    private SelectExecutor selectExecutor;

    private Map<SqlType, SqlExecutor> executors = new HashMap<>();

    @PostConstruct
    public void init() {
        executors.put(SqlType.SELECT, selectExecutor::executeSelect);
        executors.put(SqlType.DELETE, nonSelectExecutor::executeNonSelect);
        executors.put(SqlType.INSERT, nonSelectExecutor::executeNonSelect);
        executors.put(SqlType.UPDATE, nonSelectExecutor::executeNonSelect);
    }

    public SqlExecutor get(SqlType type) {
        return executors.get(type);
    }
}
