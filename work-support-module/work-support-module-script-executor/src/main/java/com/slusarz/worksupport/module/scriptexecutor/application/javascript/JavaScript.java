package com.slusarz.worksupport.module.scriptexecutor.application.javascript;

import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;

public interface JavaScript {

    ScriptName getName();

    void run(String fileName, Script script);

}
