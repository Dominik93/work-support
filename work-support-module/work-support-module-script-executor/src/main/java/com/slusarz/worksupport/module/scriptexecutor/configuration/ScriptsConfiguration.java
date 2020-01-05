package com.slusarz.worksupport.module.scriptexecutor.configuration;

import com.slusarz.worksupport.module.scriptexecutor.configuration.config.ScriptConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties("script-executor")
public class ScriptsConfiguration {

    private List<ScriptConfig> scripts;

}
