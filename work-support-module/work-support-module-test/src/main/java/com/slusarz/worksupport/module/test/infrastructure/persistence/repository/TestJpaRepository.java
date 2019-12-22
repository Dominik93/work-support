package com.slusarz.worksupport.module.test.infrastructure.persistence.repository;

import com.slusarz.worksupport.module.test.application.TestRepository;
import com.slusarz.worksupport.module.test.domain.login.Login;
import com.slusarz.worksupport.module.test.domain.code.Code;
import com.slusarz.worksupport.module.test.infrastructure.persistence.entity.TestEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TestJpaRepository implements TestRepository {

    private static final String SELECT_CODE = "SELECT s FROM TestEntity s WHERE s.login = :login";
    private static final String USER_LOGIN_PARAM = "login";

    @PersistenceContext
    private EntityManager entityManager;

    public Code getCode(final Login login) {
        TestEntity testEntity = (TestEntity) entityManager.createQuery(SELECT_CODE)
                .setParameter(USER_LOGIN_PARAM, login.getLogin())
                .getSingleResult();
        return Code.of(testEntity.getCode());
    }

}
