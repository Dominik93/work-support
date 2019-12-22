package com.slusarz.worksupport.tenant.domain.tenant;


import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.DefaultDatabase;
import com.slusarz.worksupport.tenant.application.database.validator.DatabaseTenantValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatabaseTenantFactory {

    private DatabaseTenantValidator databaseTenantValidator;

    public Database create(String tenant) {
        DefaultDatabase defaultDatabase = DefaultDatabase.of(tenant.split(TenantConstants.SEPARATOR)[1]);
        databaseTenantValidator.validate(defaultDatabase);
        return defaultDatabase;
    }

}
