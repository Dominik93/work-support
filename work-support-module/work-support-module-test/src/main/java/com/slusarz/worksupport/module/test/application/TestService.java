package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.module.test.domain.login.Login;
import com.slusarz.worksupport.module.test.domain.code.Code;
import com.slusarz.worksupport.module.test.security.TestAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@TestAuthorize
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private LoginsProvider loginsProvider;

    @Transactional
    public Code getCode(Login login) {
        return testRepository.getCode(login);
    }

    public List<Login> getLogins() {
        return loginsProvider.getLogins();
    }
}
