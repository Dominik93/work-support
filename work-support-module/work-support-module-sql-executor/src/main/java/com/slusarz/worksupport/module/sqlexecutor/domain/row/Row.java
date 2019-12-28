package com.slusarz.worksupport.module.sqlexecutor.domain.row;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
public class Row {

    @Getter
    @Singular
    private List<String> values;

}