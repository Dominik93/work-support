package com.slusarz.worksupport.module.sqlexecutor.domain.statement;


import com.slusarz.worksupport.module.sqlexecutor.domain.type.SqlType;
import lombok.Value;

@Value(staticConstructor = "of")
public class SqlStatement {

    private String statement;

    private SqlType type;

}