package com.slusarz.worksupport.module.scriptexecutor.controller;

import com.slusarz.worksupport.init.config.ModuleConfig;
import com.slusarz.worksupport.init.context.HttpContextProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.scriptexecutor.domain.pemission.PermissionNames;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ScriptExecutorModuleInitProvider implements HttpContextProvider {

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    @Autowired
    private PermissionsProvider permissionsProvider;

    @Override
    public ModuleInit init(@RequestBody ModuleInit moduleInit) {
        ModuleConfig moduleConfig = ModuleConfig.of(
                environmentConfiguration.getAvailableEnvironments(),
                environmentConfiguration.getDefaultEnvironment(),
                Collections.emptyList(),
                null);
        moduleInit.getConfig().setScriptExecutor(moduleConfig);
        moduleInit.getModuleActions().setScriptExecutor(permissionsProvider.hasPermission(PermissionNames.SCRIPT_EXECUTOR_MODULE));
        return moduleInit;
    }

}
