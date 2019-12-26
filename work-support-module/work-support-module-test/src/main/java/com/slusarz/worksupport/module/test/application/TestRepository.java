package com.slusarz.worksupport.module.test.application;

import com.slusarz.worksupport.module.test.domain.code.Code;
import com.slusarz.worksupport.module.test.domain.login.Login;

public interface TestRepository {

    Code getCode(final Login login);

}
