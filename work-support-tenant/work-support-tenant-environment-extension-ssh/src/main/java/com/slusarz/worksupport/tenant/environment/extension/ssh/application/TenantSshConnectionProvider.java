package com.slusarz.worksupport.tenant.environment.extension.ssh.application;

import com.slusarz.worksupport.commontypes.application.DefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.ssh.configuration.DefaultSshConfiguration;
import com.slusarz.worksupport.ssh.configuration.config.SshConfiguration;
import com.slusarz.worksupport.ssh.configuration.config.SshConnectionConfig;
import com.slusarz.worksupport.ssh.domain.conection.SshConnectionProperties;
import com.slusarz.worksupport.ssh.extenstion.SshConnectionPropertiesProvider;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Provider
public class TenantSshConnectionProvider implements SshConnectionPropertiesProvider {

    @Autowired
    private SshConfiguration sshConfiguration;

    @Autowired
    private DefaultSshConfiguration defaultSshConfiguration;

    private Map<Environment, SshConnectionProperties> sshProperties;

    @PostConstruct
    public void init() {
        sshProperties =  sshConfiguration.getConnections().stream()
                .collect(Collectors.toMap(SshConnectionConfig::getEnvironment, Function.identity()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> Environment.of(entry.getKey()),
                        entry -> toSshConnectionsProperties(entry.getValue())));

    }

    @Override
    public SshConnectionProperties getSshConnectionProperties() {
        return sshProperties.get(EnvironmentTenantContext.getCurrentTenant());
    }

    private SshConnectionProperties toSshConnectionsProperties(SshConnectionConfig sshConnectionConfig) {
        DefaultPropertiesHelper<SshConnectionConfig, String> helper
                = new DefaultPropertiesHelper<>(defaultSshConfiguration.getSshConnectionConfig(), sshConnectionConfig);

        return SshConnectionProperties.of(
                helper.getOrDefault(SshConnectionConfig::getUser),
                helper.getOrDefault(SshConnectionConfig::getPassword),
                helper.getOrDefault(SshConnectionConfig::getIp),
                Integer.valueOf(helper.getOrDefault(SshConnectionConfig::getPort))
        );
    }
}
