package com.slusarz.worksupport.module.sqlexecutor.domain.result;

import com.slusarz.worksupport.module.sqlexecutor.domain.row.Row;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
public class SqlResult {

    @Getter
    @Singular
    private List<String> headers;

    @Getter
    @Singular
    private List<Row> rows;

    @Getter
    private boolean success;

}
