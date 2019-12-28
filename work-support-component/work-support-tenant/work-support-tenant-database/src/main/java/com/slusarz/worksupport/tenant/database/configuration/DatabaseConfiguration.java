package com.slusarz.worksupport.tenant.database.configuration;

import com.slusarz.worksupport.commontypes.domain.Database;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class DatabaseConfiguration {

    @Value("${tenant.database.default}")
    private Database defaultDatabase;

    @Value("${tenant.database.any:false}")
    private boolean anyDatabase;

    @Value("${tenant.database.available:}#{T(java.util.Collections).emptyList()}")
    private List<Database> availableDatabases;

}
