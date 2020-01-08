package com.slusarz.worksupport.module.scriptexecutor.specification;

import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptRequest;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.ExecuteScriptResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptOutputResponse;
import com.slusarz.worksupport.module.scriptexecutor.specification.model.GetScriptsResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface ScriptExecutorApi {

    @GetMapping(value = "/scripts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetScriptsResponse getScripts();

    @PostMapping(value = "/scripts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ExecuteScriptResponse executeScript(ExecuteScriptRequest executeScriptRequest);

    @GetMapping(value = "/scripts/output/{token}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetScriptOutputResponse getScriptOutput(String token);

}
