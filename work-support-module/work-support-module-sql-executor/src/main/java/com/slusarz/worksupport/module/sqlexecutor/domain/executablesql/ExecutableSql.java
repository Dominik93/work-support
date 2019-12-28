package com.slusarz.worksupport.module.sqlexecutor.domain.executablesql;

import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class ExecutableSql {

    private SqlName sqlName;

    private List<SqlParameter> sqlParameters;

}
