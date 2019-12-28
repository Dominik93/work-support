package com.slusarz.worksupport.module.context.controller;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.init.context.ModuleInit;
import com.slusarz.worksupport.module.context.application.moduleinit.ModuleInitService;
import com.slusarz.worksupport.module.context.controller.mapper.InitResponseMapper;
import com.slusarz.worksupport.module.context.specification.ContextApi;
import com.slusarz.worksupport.module.context.specification.model.ChangeContextRequest;
import com.slusarz.worksupport.module.context.specification.model.InitResponse;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContextController implements ContextApi {

    @Autowired
    private ModuleInitService moduleInitService;

    @Autowired
    private InitResponseMapper initResponseMapper;

    @Override
    public InitResponse init() {
        ModuleInit moduleInit = moduleInitService.getModuleInit();
        return initResponseMapper.toInitResponse(moduleInit);
    }

    @Override
    public InitResponse change(@RequestBody ChangeContextRequest changeContextRequest) {
        EnvironmentTenantContext.setCurrentTenant(Environment.of(changeContextRequest.getEnvironment()));
        ModuleInit moduleInit = moduleInitService.getModuleInit();
        return initResponseMapper.toInitResponse(moduleInit);
    }

}
