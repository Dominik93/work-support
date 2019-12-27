package com.slusarz.worksupport.swagger.apiinfo;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Provider
public class DefaultApiInfoProvider implements ApiInfoProvider {

    @Value("${spring.application.name}")
    private String moduleName;

    @Value("${spring.application.version:unknown}")
    private String version;

    public ApiInfo provide() {
        return new ApiInfoBuilder()
                .title(moduleName)
                .description("Swagger for " + moduleName + " module")
                .contact(new Contact("Dominik Ślusarz",
                        "",
                        "slusarz.dominik@gmail.com"))
                .version(version)
                .build();
    }

}
