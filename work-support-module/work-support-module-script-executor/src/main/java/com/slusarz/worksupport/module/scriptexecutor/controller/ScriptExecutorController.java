package com.slusarz.worksupport.module.scriptexecutor.controller;

import com.slusarz.worksupport.module.scriptexecutor.application.script.ScriptService;
import com.slusarz.worksupport.module.scriptexecutor.controller.mapper.OptionMapper;
import com.slusarz.worksupport.module.scriptexecutor.controller.mapper.ScriptMapper;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Option;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptOutput;
import com.slusarz.worksupport.module.scriptexecutor.specification.ScriptExecutorApi;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptRequest;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptOutputResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScriptExecutorController implements ScriptExecutorApi {

    @Autowired
    private ScriptService scriptService;

    @Autowired
    private ScriptMapper scriptMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public GetScriptsResponse getScripts() {
        List<Script> scripts = scriptService.getScripts();
        return GetScriptsResponse
                .builder()
                .scripts(scriptMapper.toScripts(scripts))
                .build();
    }

    @Override
    public ExecuteScriptResponse executeScript(@RequestBody ExecuteScriptRequest executeScriptRequest) {
        List<Option> options = optionMapper.toOptions(executeScriptRequest.getOptions());
        ScriptName scriptName = ScriptName.of(executeScriptRequest.getScriptName());
        String token = scriptService.runScript(scriptName, options);
        return ExecuteScriptResponse.success(token);
    }

    @Override
    public GetScriptOutputResponse getScriptOutput(@PathVariable("token") String token) {
        ScriptOutput scriptOutput = scriptService.readFile(token);
        return GetScriptOutputResponse
                .builder()
                .content(scriptOutput.getContent())
                .allFetched(scriptOutput.isAllFetched())
                .build();
    }

}
