package com.slusarz.worksupport.module.sqlexecutor.application.service;

import com.slusarz.worksupport.module.sqlexecutor.domain.parameter.SqlParameter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SqlParameterRetriever {

    private static final String PARAMETER_PREFIX = ":";
    private static final String END_OF_QUERY = ";";

    public List<SqlParameter> retrieveSqlParameters(String sql) {
        List<SqlParameter> sqlParameters = new ArrayList<>();
        int lastFoundChar = 0;
        int parameterCount = sql.split(PARAMETER_PREFIX).length - 1;
        while (sqlParameters.size() < parameterCount) {
            lastFoundChar = sql.indexOf(PARAMETER_PREFIX, lastFoundChar);
            int spaceCharacter = sql.indexOf(" ", lastFoundChar);
            int semiColonCharacter = sql.indexOf(END_OF_QUERY, lastFoundChar);
            int min = Arrays.stream(new int[]{semiColonCharacter, spaceCharacter})
                    .filter(i -> i >= 0)
                    .min()
                    .orElse(sql.length());
            lastFoundChar++;
            String parameter = sql.substring(lastFoundChar, min);
            sqlParameters.add(SqlParameter.of(parameter));
        }
        return sqlParameters.stream().distinct().collect(Collectors.toList());
    }

}
