package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScriptExtension {

    SH("sh", "sh"),
    BAT("", "bat");

    private String call;

    private String extension;

    public static ScriptExtension getExtension(String extension) {
        if (BAT.getExtension().equals(extension)) {
            return BAT;
        }
        return SH;
    }

}
