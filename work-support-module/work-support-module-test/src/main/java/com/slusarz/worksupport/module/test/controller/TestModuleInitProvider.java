package com.slusarz.worksupport.module.test.controller;

import com.slusarz.worksupport.commontypes.application.environment.EnvironmentProvider;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
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
        DefaultEnvironment defaultEnvironment = DefaultEnvironment.of(environmentProvider.provide().getName());
        List<DefaultEnvironment> list = Collections.singletonList(defaultEnvironment);
        ModuleConfig test = ModuleConfig.of(list, defaultEnvironment, Collections.emptyList(), null);
        moduleInit.getConfig().setTest(test);
        return moduleInit;
    }

}
