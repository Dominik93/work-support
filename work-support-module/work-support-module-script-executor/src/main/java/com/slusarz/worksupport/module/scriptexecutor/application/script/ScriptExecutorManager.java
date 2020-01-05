package com.slusarz.worksupport.module.scriptexecutor.application.script;

import com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor.ScriptExecutor;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScriptExecutorManager {

    @Autowired
    private List<ScriptExecutor> scriptExecutors;

    private Map<ConnectionType, ScriptExecutor> scriptExecutorsByConnectionType = new HashMap<>();

    @PostConstruct
    public void init() {
        for (ScriptExecutor commandsExecutor : scriptExecutors) {
            scriptExecutorsByConnectionType.put(commandsExecutor.getConnection(), commandsExecutor);
        }
    }

    public ScriptExecutor get(ConnectionType connectionType) {
        return scriptExecutorsByConnectionType.get(connectionType);
    }


}
