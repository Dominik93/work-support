package com.slusarz.worksupport.module.scriptexecutor.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Argument;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Option;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties
public class ScriptConfig {

    private String name;

    private List<Environment> environments;

    private ConnectionType connectionType;

    private boolean monitoring;

    private String script;

    private List<Argument> arguments;

    private List<Option> options;

}
