package com.slusarz.worksupport.tenant.database.domain;


import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import com.slusarz.worksupport.tenant.database.application.validator.DatabaseTenantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTenantFactory {

    @Autowired
    private DatabaseTenantValidator databaseTenantValidator;

    public Database create(String tenant) {
        Database defaultDatabase = Database.of(tenant.split(TenantConstants.SEPARATOR)[1]);
        databaseTenantValidator.validate(defaultDatabase);
        return defaultDatabase;
    }

}
