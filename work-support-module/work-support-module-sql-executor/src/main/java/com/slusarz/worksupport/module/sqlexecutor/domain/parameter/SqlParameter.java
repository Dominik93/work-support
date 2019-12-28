package com.slusarz.worksupport.module.sqlexecutor.domain.parameter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlParameter {

    private String name;

    private SqlParameterValue option;

    public static SqlParameter of(final String name) {
        return SqlParameter.of(name, "");
    }

    public static SqlParameter of(final String name, final String value) {
        return new SqlParameter(name, SqlParameterValue.of(name, value));
    }

}
