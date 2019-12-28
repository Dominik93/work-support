package com.slusarz.worksupport.init.context;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/context")
public interface HttpContextProvider extends ContextProvider{

    @PostMapping(value = "/init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ModuleInit init(@RequestBody ModuleInit moduleInit);

}
