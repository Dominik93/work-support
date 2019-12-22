package com.slusarz.worksupport.init.config;

import com.slusarz.worksupport.commontypes.domain.DefaultDatabase;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import lombok.Value;

import java.util.List;


@Value(staticConstructor = "of")
public class ModuleConfig {

    private List<DefaultEnvironment> environments;

    private DefaultEnvironment defaultEnvironment;

    private List<DefaultDatabase> databases;

    private DefaultDatabase defaultDatabase;

}
