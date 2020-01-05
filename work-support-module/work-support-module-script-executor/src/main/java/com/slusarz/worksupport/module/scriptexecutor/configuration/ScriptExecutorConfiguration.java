package com.slusarz.worksupport.module.scriptexecutor.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ScriptExecutorConfiguration {

    @Value("${script-executor.script-directory}")
    private String directory;

    @Value("${script-executor.executed-script-directory}")
    private String directoryExecuted;

}
