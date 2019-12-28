package com.slusarz.worksupport.tenant.database.application.validator;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.tenant.database.application.exceptions.DatabaseNotSupportedRuntimeException;
import com.slusarz.worksupport.tenant.database.configuration.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTenantValidator {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    public void validate(Database database) {
        if (!(anyEnvironment() || isAvailable(database))) {
            throw new DatabaseNotSupportedRuntimeException(databaseConfiguration.getAvailableDatabases(), database);
        }
    }

    private boolean isAvailable(Database database){
        return databaseConfiguration.getAvailableDatabases().contains(database);
    }

    private boolean anyEnvironment() {
        return databaseConfiguration.isAnyDatabase();
    }

}
