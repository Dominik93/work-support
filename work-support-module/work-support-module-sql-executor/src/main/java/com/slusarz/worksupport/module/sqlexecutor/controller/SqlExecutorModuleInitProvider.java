package com.slusarz.worksupport.module.sqlexecutor.controller;

import com.slusarz.worksupport.init.config.ModuleConfig;
import com.slusarz.worksupport.init.context.HttpContextProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.sqlexecutor.domain.permission.PermissionNames;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import com.slusarz.worksupport.tenant.database.configuration.DatabaseConfiguration;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqlExecutorModuleInitProvider implements HttpContextProvider {

    @Autowired
    private DatabaseConfiguration databaseConfiguration;

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    @Autowired
    private PermissionsProvider permissionsProvider;

    @Override
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
