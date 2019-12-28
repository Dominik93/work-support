package com.slusarz.worksupport.module.sqlexecutor.domain.metadata;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.module.sqlexecutor.domain.description.SqlDescription;
import com.slusarz.worksupport.module.sqlexecutor.domain.name.SqlName;
import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import com.slusarz.worksupport.module.sqlexecutor.domain.type.SqlType;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class SqlMetadata {

    private SqlName sqlName;

    private SqlDescription sqlDescription;

    private Database database;

    private SqlType type;

    private List<SqlParameter> sqlParameters;

}
