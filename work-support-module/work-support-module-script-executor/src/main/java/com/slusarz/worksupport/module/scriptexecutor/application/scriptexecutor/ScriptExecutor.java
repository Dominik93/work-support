package com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor;

import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;

public interface ScriptExecutor {

    ConnectionType getConnection();

    void execute(String fileName, Script script);

}
