package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ScriptOutput {

    private String content;

    private boolean allFetched;

}
