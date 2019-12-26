package com.slusarz.worksupport.tenant.database.application.validator;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.tenant.database.application.exceptions.DatabaseNotSupportedRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseTenantValidator {

    @Value("${tenant.database.any:false}")
    private boolean anyDatabase;

    @Value("${tenant.database.available:}#{T(java.util.Collections).emptyList()}")
    private List<Database> availableDatabases;

    public void validate(Database database) {
        if (!(anyEnvironment() || isAvailable(database))) {
            throw new DatabaseNotSupportedRuntimeException(availableDatabases, database);
        }
    }

    private boolean isAvailable(Database database){
        return availableDatabases.contains(database);
    }

    private boolean anyEnvironment() {
        return anyDatabase;
    }

}
