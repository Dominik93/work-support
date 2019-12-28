package com.slusarz.worksupport.module.sqlexecutor.domain.parameter;

import lombok.Value;

import java.util.Arrays;

@Value(staticConstructor = "of")
public class SqlParameterValue {

    private String value;

    private boolean list;

    public static SqlParameterValue of(final String optionName, final String value) {
        return new SqlParameterValue(value, optionName.contains("list") || optionName.contains("List"));
    }

    public Object getJdbcValue() {
        if (list) {
            return Arrays.asList(value.split(","));
        }
        return value;
    }

}
