package com.slusarz.worksupport.module.test.controller;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.init.config.ModuleConfig;
import com.slusarz.worksupport.init.context.HttpContextProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TestModuleInitProvider implements HttpContextProvider {

    @Provide
    private EnvironmentProvider environmentProvider;

    @Override
    public ModuleInit init(@RequestBody ModuleInit moduleInit) {
        Environment environment = Environment.of(environmentProvider.provide().getName());
        List<Environment> list = Collections.singletonList(environment);
        ModuleConfig test = ModuleConfig.of(list, environment, Collections.emptyList(), null);
        moduleInit.getConfig().setTest(test);
        return moduleInit;
    }

}
