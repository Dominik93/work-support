package com.slusarz.worksupport.module.test.specification;

import com.slusarz.worksupport.module.test.specification.model.GetTestCodeResponse;
import com.slusarz.worksupport.module.test.specification.model.GetTestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public interface TestApi {

    @GetMapping(value = "/code/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetTestCodeResponse getCode(String userId);

    @GetMapping(value = "/logins", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetTestResponse getLogins();

}
