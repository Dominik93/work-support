package com.slusarz.worksupport.tenant.environment.application.context;

import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class EnvironmentTenantContext {

    private static InheritableThreadLocal<Environment> currentTenant = new InheritableThreadLocal<>();

    @Setter
    private static Environment defaultEnvironment;


    public static void init(Environment environment) {
        if (Objects.nonNull(defaultEnvironment)) {
            throw new RuntimeException("Environment tenant already initialized");
        }
        defaultEnvironment = environment;
    }

    public static Environment getCurrentTenant() {
        Environment env = Optional.ofNullable(currentTenant.get())
                .orElse(defaultEnvironment);
        log.debug("Current environment [" + env.toString() + "]");
        return env;
    }

    public static void setCurrentTenant(Environment tenant) {
        log.debug("Set tenant [" + tenant + "]");
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }
}
