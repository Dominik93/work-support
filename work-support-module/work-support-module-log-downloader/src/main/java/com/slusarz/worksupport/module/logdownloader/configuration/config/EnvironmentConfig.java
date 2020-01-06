package com.slusarz.worksupport.module.logdownloader.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
public class EnvironmentConfig {

    private Environment environment;

    private List<Server> servers;

    private Connection connection;

    private String path;
}
