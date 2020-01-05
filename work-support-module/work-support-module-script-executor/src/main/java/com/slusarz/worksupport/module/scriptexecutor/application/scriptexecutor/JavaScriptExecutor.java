package com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor;

import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.module.scriptexecutor.application.javascript.JavaScript;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Executor
public class JavaScriptExecutor implements ScriptExecutor {

    @Autowired
    private List<JavaScript> javaScripts;

    private Map<ScriptName, JavaScript> javaScriptMapByScriptName;

    @PostConstruct
    public void init() {
        javaScriptMapByScriptName = javaScripts.stream()
                .collect(Collectors.toMap(JavaScript::getName, Function.identity()));
    }

    @Override
    public ConnectionType getConnection() {
        return ConnectionType.JAVA;
    }

    @Async
    public void execute(final String fileName, final Script script) {
        javaScriptMapByScriptName.get(script.getScriptName()).run(fileName, script);
    }


}
