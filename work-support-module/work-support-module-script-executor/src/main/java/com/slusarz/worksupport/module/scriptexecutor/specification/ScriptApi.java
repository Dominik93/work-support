package com.slusarz.worksupport.module.scriptexecutor.specification;

import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptRequest;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptOutputResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/scripts")
public interface ScriptApi {

    @GetMapping
    GetScriptsResponse getScripts();

    @PostMapping
    ExecuteScriptResponse executeScript(ExecuteScriptRequest executeScriptRequest);

    @GetMapping("/output/{token}")
    GetScriptOutputResponse getScriptOutput(String token);

}
