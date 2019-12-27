package com.slusarz.worksupport.swagger;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.swagger.apiinfo.ApiInfoProvider;
import com.slusarz.worksupport.swagger.parameter.TenantParameterProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
@ComponentScan("com.slusarz.worksupport.swagger")
public class SwaggerConfiguration {

    @Value("${spring.application.name}")
    private String moduleName;

    @Provide
    private ApiInfoProvider apiInfoProvider;

    @Provide
    private TenantParameterProvider tenantParameterProvider;

    @Bean
    public Docket swaggerApi() {
        ResponseMessageBuilder responseMessageBuilder = new ResponseMessageBuilder();
        responseMessageBuilder
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(moduleName + "-api")
                .apiInfo(apiInfoProvider.provide())
                .globalOperationParameters(Arrays.asList(tenantParameterProvider.provide()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(responseMessageBuilder.build()));
    }


}
