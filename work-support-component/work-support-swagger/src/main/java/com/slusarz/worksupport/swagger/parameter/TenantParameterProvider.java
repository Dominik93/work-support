package com.slusarz.worksupport.swagger.parameter;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Database;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.domain.TenantConstants;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;

import java.util.Optional;

@Provider
public class TenantParameterProvider {

    @Value("${tenant.database.default:#{null}}")
    private Database database;

    @Value("${tenant.environment.default:#{null}}")
    private Environment environment;

    public Parameter provide() {
        return new ParameterBuilder()
                .name(TenantConstants.TENANT_HEADER_NAME)
                .defaultValue(getDefaultValue())
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
    }

    private String getDefaultValue() {
        return Optional.ofNullable(environment).map(Environment::getName).orElse("")
                + "-" +
                Optional.ofNullable(database).map(Database::getName).orElse("");
    }

}
