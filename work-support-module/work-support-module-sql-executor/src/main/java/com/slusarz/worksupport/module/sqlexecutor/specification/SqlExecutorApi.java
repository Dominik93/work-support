package com.slusarz.worksupport.module.sqlexecutor.specification;

import com.slusarz.worksupport.module.sqlexecutor.specification.model.ExecuteSqlRequest;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.ExecuteSqlResponse;
import com.slusarz.worksupport.module.sqlexecutor.specification.model.GetSqlResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public interface SqlExecutorApi {

    @PostMapping(value = "/sql", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ExecuteSqlResponse executeSqlResponse(ExecuteSqlRequest executeSqlRequest);

    @GetMapping(value = "/sql", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetSqlResponse getSql();


}
