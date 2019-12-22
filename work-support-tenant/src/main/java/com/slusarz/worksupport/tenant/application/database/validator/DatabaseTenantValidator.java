package com.slusarz.worksupport.tenant.application.database.validator;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.tenant.application.database.exceptions.DatabaseNotSupportedRuntimeException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DatabaseTenantValidator {

    private List<Database> availableDatabases;

    public void validate(Database database) {
        if (!availableDatabases.contains(database)) {
            throw new DatabaseNotSupportedRuntimeException(availableDatabases, database);
        }
    }

}
