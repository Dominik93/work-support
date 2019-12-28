package com.slusarz.worksupport.module.sqlexecutor.domain.statement.comparator;

import com.slusarz.worksupport.module.sqlexecutor.domain.statement.SqlStatement;

import java.util.Comparator;

public class SqlStatementComparator implements Comparator<SqlStatement> {
    @Override
    public int compare(SqlStatement o1, SqlStatement o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
