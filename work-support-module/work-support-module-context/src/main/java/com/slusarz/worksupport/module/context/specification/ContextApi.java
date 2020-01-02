package com.slusarz.worksupport.module.context.specification;

import com.slusarz.worksupport.module.context.specification.model.ChangeContextRequest;
import com.slusarz.worksupport.module.context.specification.model.ChangeContextResponse;
import com.slusarz.worksupport.module.context.specification.model.InitResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface ContextApi {

    @GetMapping(value = "/init", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    InitResponse init();

    @PostMapping(value = "/change", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ChangeContextResponse change(ChangeContextRequest changeContextRequest);

}
