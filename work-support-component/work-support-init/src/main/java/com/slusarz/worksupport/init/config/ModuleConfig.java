package com.slusarz.worksupport.init.config;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Value(staticConstructor = "of")
public class ModuleConfig {

    private List<Environment> environments;

    private Environment defaultEnvironment;

    private List<Database> databases;

    private Database defaultDatabase;

    public static ModuleConfig ofDefault() {
        return new ModuleConfig(Collections.emptyList(), null, Collections.emptyList(), null);
    }

    public Optional<Environment> getDefaultEnvironment() {
        return Optional.ofNullable(defaultEnvironment);
    }

    public Optional<Database> getDefaultDatabase() {
        return Optional.ofNullable(defaultDatabase);
    }
}
