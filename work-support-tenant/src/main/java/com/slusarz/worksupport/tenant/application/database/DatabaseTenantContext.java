package com.slusarz.worksupport.tenant.application.database;

import com.slusarz.worksupport.commontypes.domain.Database;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class DatabaseTenantContext {

    private static InheritableThreadLocal<Database> currentTenant = new InheritableThreadLocal<>();

    private static Database defaultDatabase;

    public static void init(Database database) {
        if (Objects.nonNull(defaultDatabase)) {
            throw new RuntimeException("Environment tenant already initialized");
        }
        defaultDatabase = database;
    }

    public static Database getCurrentTenant() {
        Database database = Optional.ofNullable(currentTenant.get())
                .orElse(defaultDatabase);
        log.debug("Current database [" + database.toString() + "]");
        return database;
    }

    public static void setCurrentTenant(Database tenant) {
        log.debug("Set tenant [" + tenant + "]");
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
