package com.slusarz.worksupport.module.sqlexecutor.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlType {

    SELECT(false),
    DELETE(true),
    UPDATE(true),
    INSERT(true);

    private boolean modificationQuery;


}
