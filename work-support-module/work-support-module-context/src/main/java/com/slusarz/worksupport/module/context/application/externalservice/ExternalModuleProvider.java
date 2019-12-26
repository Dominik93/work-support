package com.slusarz.worksupport.module.context.application.externalservice;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.module.context.domain.ExternalModule;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Provider
public class ExternalModuleProvider {

    @Value("${context.services}")
    private List<ExternalModule> externalModules;

    public List<ExternalModule> provide() {
        return externalModules;
    }
}
