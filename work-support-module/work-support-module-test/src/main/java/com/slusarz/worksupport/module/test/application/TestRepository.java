package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.module.test.domain.login.Login;
import com.slusarz.worksupport.module.test.domain.code.Code;

public interface TestRepository {

    Code getCode(final Login login);

}
