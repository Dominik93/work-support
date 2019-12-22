package com.slusarz.worksupport.module.context.application.moduleinit;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.init.context.ContextPathProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.context.application.externalservice.ExternalModuleProvider;
import com.slusarz.worksupport.module.context.domain.ExternalModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class ModuleInitService {

    @Provide
    private ExternalModuleProvider externalModuleProvider;

    @Autowired
    private RestTemplate restTemplate;

    public ModuleInit getModuleInit() {
        ModuleInit moduleInit = ModuleInit.empty();
        for (ExternalModule externalModule : externalModuleProvider.provide()) {
            Optional<ModuleInit> optionalModuleInit = callExternalModuleInit(moduleInit, externalModule);
            if (optionalModuleInit.isPresent()) {
                moduleInit = optionalModuleInit.get();
            }
        }
        return moduleInit;
    }

    private Optional<ModuleInit> callExternalModuleInit(ModuleInit moduleInit, ExternalModule externalModule) {
        try {
            ModuleInit init = restTemplate.postForObject(ContextPathProvider.path(externalModule.getName()), moduleInit, ModuleInit.class);
            return Optional.ofNullable(init);
        } catch (Exception e) {
            log.info("Error while collect context from [" + externalModule.getName() + "]. Reason: " + e.getMessage());
        }
        return Optional.empty();
    }


}
