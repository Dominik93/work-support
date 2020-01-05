package com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor;

import com.slusarz.worksupport.module.scriptexecutor.domain.script.Argument;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Option;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RunCommandPreparator {

    public String prepareRunCommand(Script script) {
        return String.format("%s\\%s.%s %s %s",
                script.getScriptFile().getPath(),
                script.getScriptFile().getFile(),
                script.getScriptFile().getExtension().getExtension(),
                prepareArguments(script.getArguments()),
                prepareOptions(script.getOptions()));
    }

    private String prepareArguments(List<Argument> arguments) {
        return arguments.stream().map(Argument::toArgument).collect(Collectors.joining(" "));
    }

    private String prepareOptions(List<Option> options) {
        return options.stream().map(Option::toOption).collect(Collectors.joining(" "));
    }

}
