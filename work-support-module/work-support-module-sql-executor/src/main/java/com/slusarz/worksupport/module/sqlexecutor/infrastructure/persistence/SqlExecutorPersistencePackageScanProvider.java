package com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.multitenancy.database.extension.PersistencePackageScanProvider;

@Provider
public class SqlExecutorPersistencePackageScanProvider implements PersistencePackageScanProvider {

    @Override
    public String[] provide() {
        return new String[]{"com.slusarz.worksupport.module.sqlexecutor.infrastructure.persistence"};
    }

}
