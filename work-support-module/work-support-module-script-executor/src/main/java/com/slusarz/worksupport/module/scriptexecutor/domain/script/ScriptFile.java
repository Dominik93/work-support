package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.Value;

@Value(staticConstructor = "of")
public class ScriptFile {

    private String path;

    private String file;

    private ScriptExtension extension;

}
