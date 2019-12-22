package com.slusarz.worksupport.module.context.controller;

import com.slusarz.worksupport.commontypes.domain.DefaultDatabase;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.context.application.moduleinit.ModuleInitService;
import com.slusarz.worksupport.module.context.specification.ContextApi;
import com.slusarz.worksupport.module.context.specification.model.ChangeContextRequest;
import com.slusarz.worksupport.module.context.specification.model.Config;
import com.slusarz.worksupport.module.context.specification.model.InitResponse;
import com.slusarz.worksupport.module.context.specification.model.ModuleConfig;
import com.slusarz.worksupport.tenant.application.environment.EnvironmentTenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class ContextController implements ContextApi {

    @Autowired
    private ModuleInitService moduleInitService;

    @Override
    public InitResponse init() {
        InitResponse initResponse = new InitResponse();
        ModuleInit moduleInit = moduleInitService.getModuleInit();
        initResponse.setConfig(toConfig(moduleInit.getConfig()));
        return initResponse;
    }

    @Override
    public InitResponse change(@RequestBody ChangeContextRequest changeContextRequest) {
        EnvironmentTenantContext.setCurrentTenant(DefaultEnvironment.of(changeContextRequest.getEnvironment()));
        InitResponse initResponse = new InitResponse();
        ModuleInit moduleInit = moduleInitService.getModuleInit();
        initResponse.setConfig(toConfig(moduleInit.getConfig()));
        return initResponse;
    }

    private Config toConfig(com.slusarz.worksupport.init.config.Config config) {
        Config conf = new Config();
        ModuleConfig test = new ModuleConfig();
        test.setDatabases(config.getTest().getDatabases().stream().map(DefaultDatabase::getName).collect(Collectors.toList()));
        test.setDefaultDatabase(null);
        test.setDefaultEnvironment(config.getTest().getDefaultEnvironment().getName());
        test.setEnvironments(config.getTest().getEnvironments().stream().map(DefaultEnvironment::getName).collect(Collectors.toList()));
        conf.setTest(test);
        return conf;
    }
}
