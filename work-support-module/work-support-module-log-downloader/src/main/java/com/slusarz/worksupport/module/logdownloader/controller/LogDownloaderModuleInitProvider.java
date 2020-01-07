package com.slusarz.worksupport.module.logdownloader.controller;

import com.slusarz.worksupport.init.actions.LogDownloaderActions;
import com.slusarz.worksupport.init.config.ModuleConfig;
import com.slusarz.worksupport.init.context.HttpContextProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.logdownloader.domain.permission.PermissionNames;
import com.slusarz.worksupport.permission.extension.PermissionsProvider;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class LogDownloaderModuleInitProvider implements HttpContextProvider {

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
        moduleInit.getConfig().setLogDownloader(moduleConfig);
        moduleInit.getActions().setLogDownloaderActions(LogDownloaderActions.of(
                permissionsProvider.hasPermission(PermissionNames.LOG_LIVE_LOG),
                permissionsProvider.hasPermission(PermissionNames.PACKAGE_LIVE_LOG)));
        moduleInit.getModuleActions().setLogDownloader(permissionsProvider.hasPermission(PermissionNames.LOG_DOWNLOADER_MODULE));
        return moduleInit;
    }

}
