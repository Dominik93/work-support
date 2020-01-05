package com.slusarz.worksupport.module.scriptexecutor.controller.mapper;

import com.slusarz.worksupport.module.scriptexecutor.specification.model.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScriptMapper {

    @Autowired
    private OptionMapper optionMapper;

    public List<Script> toScripts(List<com.slusarz.worksupport.module.scriptexecutor.domain.script.Script> scripts) {
        return scripts.stream().map(this::toScript).collect(Collectors.toList());
    }

    public Script toScript(com.slusarz.worksupport.module.scriptexecutor.domain.script.Script script) {
        return Script.builder()
                .monitoring(script.isMonitoring())
                .name(script.getScriptName().getName())
                .options(optionMapper.toOptionsApi(script.getOptions()))
                .build();
    }
}
