package com.slusarz.worksupport.tenant.environment.configuration;

import com.slusarz.worksupport.commontypes.domain.Environment;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class EnvironmentConfiguration {

    @Value("${tenant.environment.default}")
    private Environment defaultEnvironment;

    @Value("${tenant.environment.any:false}")
    private boolean anyEnvironment;

    @Value("${tenant.environment.available:}#{T(java.util.Collections).emptyList()}")
    private List<Environment> availableEnvironments;

}
