package com.slusarz.worksupport.init.config;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Value;

import java.util.List;


@Value(staticConstructor = "of")
public class ModuleConfig {

    private List<Environment> environments;

    private Environment environment;

    private List<Database> databases;

    private Database defaultDatabase;

}
