package com.slusarz.worksupport.module.context.controller.mapper;

import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.context.specification.model.Actions;
import com.slusarz.worksupport.module.context.specification.model.Config;
import com.slusarz.worksupport.module.context.specification.model.InitResponse;
import com.slusarz.worksupport.module.context.specification.model.LogActions;
import com.slusarz.worksupport.module.context.specification.model.ModuleActions;
import com.slusarz.worksupport.module.context.specification.model.ModuleConfig;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InitResponseMapper {

    public InitResponse toInitResponse(ModuleInit moduleInit) {
        InitResponse initResponse = new InitResponse();
        initResponse.setConfig(toConfig(moduleInit.getConfig()));
        initResponse.setModuleActions(toModuleActions(moduleInit.getModuleActions()));
        initResponse.setActions(toActions(moduleInit.getActions()));
        return initResponse;
    }

    private Actions toActions(com.slusarz.worksupport.init.actions.Actions actions) {
        return Actions.builder()
                .logActions(LogActions.builder()
                        .packageLiveLog(actions.getLogActions().isPackageLiveLog())
                        .liveLog(actions.getLogActions().isLiveLog())
                        .build())
                .build();
    }

    private ModuleActions toModuleActions(com.slusarz.worksupport.init.actions.ModuleActions moduleActions) {
        return ModuleActions.builder()
                .log(moduleActions.isLog())
                .scriptExecutor(moduleActions.isScriptExecutor())
                .sqlExecutor(moduleActions.isSqlExecutor())
                .build();
    }

    private Config toConfig(com.slusarz.worksupport.init.config.Config config) {
        Config apiConfig = new Config();
        apiConfig.setSqlExecutor(toConfig(config.getSqlExecutor()));
        apiConfig.setTest(toConfig(config.getTest()));
        apiConfig.setLog(toConfig(config.getLog()));
        apiConfig.setScriptExecutor(toConfig(config.getScriptExecutor()));
        return apiConfig;
    }

    private ModuleConfig toConfig(com.slusarz.worksupport.init.config.ModuleConfig moduleConfig) {
        ModuleConfig config = new ModuleConfig();
        config.setDatabases(moduleConfig.getDatabases().stream().map(Database::getName).collect(Collectors.toList()));
        config.setDefaultDatabase(moduleConfig.getDefaultDatabase().map(Database::getName).orElse(null));
        config.setDefaultEnvironment(moduleConfig.getDefaultEnvironment().map(Environment::getName).orElse(null));
        config.setEnvironments(moduleConfig.getEnvironments().stream().map(Environment::getName).collect(Collectors.toList()));
        return config;
    }
}
