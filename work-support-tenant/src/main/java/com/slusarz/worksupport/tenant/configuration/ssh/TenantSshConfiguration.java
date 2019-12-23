package com.slusarz.worksupport.tenant.configuration.ssh;

import com.slusarz.worksupport.commontypes.application.DefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.environment.EnvironmentProvider;
import com.slusarz.worksupport.commontypes.domain.DefaultEnvironment;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.ssh.application.connection.SshConnectionPropertiesProvider;
import com.slusarz.worksupport.ssh.configuration.config.SshConfig;
import com.slusarz.worksupport.ssh.configuration.config.SshConnectionConfig;
import com.slusarz.worksupport.ssh.domain.conection.SshConnectionProperties;
import com.slusarz.worksupport.tenant.application.ssh.TenantSshConnectionProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class TenantSshConfiguration {

    @Autowired
    private SshConfig sshConfig;

    @Autowired
    private SshConnectionConfig defaultSshConnectionConfig;

    @Bean
    public SshConnectionPropertiesProvider sshPropertiesProvider(EnvironmentProvider environmentProvider) {
        Map<Environment, SshConnectionProperties> sshProperties = sshConfig.getConnections().stream()
                .collect(Collectors.toMap(SshConnectionConfig::getEnvironment, Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> DefaultEnvironment.of(entry.getKey()),
                        entry -> toSshConnectionsProperties(entry.getValue())));
        return new TenantSshConnectionProvider(sshProperties);
    }

    private SshConnectionProperties toSshConnectionsProperties(SshConnectionConfig sshConnectionConfig) {
        DefaultPropertiesHelper<SshConnectionConfig, String> helper
                = new DefaultPropertiesHelper<>(defaultSshConnectionConfig, sshConnectionConfig);

        return SshConnectionProperties.of(
                helper.getOrDefault(SshConnectionConfig::getUser),
                helper.getOrDefault(SshConnectionConfig::getPassword),
                helper.getOrDefault(SshConnectionConfig::getIp),
                Integer.valueOf(helper.getOrDefault(SshConnectionConfig::getPort))
        );
    }

}
