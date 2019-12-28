package com.slusarz.worksupport.module.context.application.moduleinit;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.init.context.ContextPathProvider;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.context.application.externalservice.ExternalModuleProvider;
import com.slusarz.worksupport.module.context.application.httpentity.HttpEntityCreator;
import com.slusarz.worksupport.module.context.domain.ExternalModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

    @Autowired
    private HttpEntityCreator httpEntityCreator;

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
            String path = ContextPathProvider.path(externalModule.getName());
            HttpEntity<ModuleInit> request = httpEntityCreator.create(moduleInit);
            ModuleInit init = restTemplate.postForObject(path, request, ModuleInit.class);
            log.info("Init from " + externalModule + ": " + init);
            return Optional.ofNullable(init);
        } catch (Exception e) {
            log.info("Error while collect context from [" + externalModule.getName() + "]. Reason: " + e.getMessage());
        }
        return Optional.empty();
    }


}
