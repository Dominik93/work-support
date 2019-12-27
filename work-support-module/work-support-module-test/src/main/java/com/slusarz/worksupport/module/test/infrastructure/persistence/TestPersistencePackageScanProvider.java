package com.slusarz.worksupport.module.test.infrastructure.persistence;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.multitenancy.database.extension.PersistencePackageScanProvider;

@Provider
public class TestPersistencePackageScanProvider implements PersistencePackageScanProvider {

    @Override
    public String[] provide() {
        return new String[]{"com.slusarz.worksupport.module.test.infrastructure.persistence"};
    }

}
