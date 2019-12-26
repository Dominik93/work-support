package com.slusarz.worksupport.module.test.controller;

import com.slusarz.worksupport.module.test.application.TestService;
import com.slusarz.worksupport.module.test.domain.code.Code;
import com.slusarz.worksupport.module.test.domain.login.Login;
import com.slusarz.worksupport.module.test.specification.TestApi;
import com.slusarz.worksupport.module.test.specification.model.GetTestCodeResponse;
import com.slusarz.worksupport.module.test.specification.model.GetTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController implements TestApi {

    @Autowired
    private TestService testService;

    @Override
    public GetTestCodeResponse getCode(@PathVariable("login") String login) {
        Code code = testService.getCode(Login.of(login));
        return GetTestCodeResponse
                .builder()
                .code(code.getCode())
                .build();
    }

    @Override
    public GetTestResponse getLogins() {
        List<Login> logins = testService.getLogins();
        return GetTestResponse
                .builder()
                .logins(logins.stream().map(Login::getLogin).collect(Collectors.toList()))
                .build();
    }
}
