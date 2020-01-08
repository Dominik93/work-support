package com.slusarz.worksupport.module.sqlexecutor.controller;

import com.slusarz.worksupport.init.config.ModuleConfig;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.sqlexecutor.domain.permission.PermissionNames;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import com.slusarz.worksupport.tenant.database.configuration.DatabaseConfiguration;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/sql-executor/context")
public class SqlExecutorModuleInitProviderttt{

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    @Autowired
    private PermissionsProvider permissionsProvider;

    @PostMapping(value = "/init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModuleInit init(@RequestBody ModuleInit moduleInit) {
        ModuleConfig moduleConfig = ModuleConfig.of(
                environmentConfiguration.getAvailableEnvironments(),
                environmentConfiguration.getDefaultEnvironment(),
                databaseConfiguration.getAvailableDatabases(),
                databaseConfiguration.getDefaultDatabase());
        moduleInit.getConfig().setSqlExecutor(moduleConfig);
        moduleInit.getModuleActions().setSqlExecutor(permissionsProvider.hasPermission(PermissionNames.SQL_EXECUTOR_MODULE));
        return moduleInit;
    }

}
