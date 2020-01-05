package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Script {

    private ScriptName scriptName;

    private ScriptFile scriptFile;

    private List<Argument> arguments;

    private List<Option> options;

    private ConnectionType connectionType;

    private boolean monitoring;

    public void fillOptions(List<Option> options) {
        List<Option> filledOptions = new ArrayList<>();
        for (Option option : this.options) {
            filledOptions.add(Option.of(option.getOption(), options.stream()
                    .filter(o -> o.getOption().equals(option.getOption()))
                    .findFirst().orElseThrow(RuntimeException::new).getValue()));
        }
        this.options = filledOptions;
    }
}
