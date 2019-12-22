package com.slusarz.worksupport.tenant.application.database.exceptions;

import com.slusarz.worksupport.commontypes.domain.Database;

import java.util.List;

public class DatabaseNotSupportedRuntimeException extends RuntimeException {

    public DatabaseNotSupportedRuntimeException(List<Database> databases, Database database) {
        super("[" + database + "] not supported. Available environmnets: " + databases);
    }

}
