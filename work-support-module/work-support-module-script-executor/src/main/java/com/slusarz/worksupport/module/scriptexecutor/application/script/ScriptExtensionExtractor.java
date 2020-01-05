package com.slusarz.worksupport.module.scriptexecutor.application.script;

import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptExtension;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;
import org.springframework.stereotype.Component;

@Component
public class ScriptExtensionExtractor {

    public ScriptExtension extract(ScriptName scriptName) {
        String name = scriptName.getName();
        String extension = name.split("\\.")[name.split("\\.").length - 1];
        return ScriptExtension.getExtension(extension);
    }

}
