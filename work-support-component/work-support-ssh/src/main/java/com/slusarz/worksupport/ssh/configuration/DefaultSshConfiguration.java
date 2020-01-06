package com.slusarz.worksupport.ssh.configuration;

import com.slusarz.worksupport.ssh.configuration.config.SshConnectionConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultSshConfiguration {

    @Value("${ssh.default.ip:#{null}}")
    private String ip;

    @Value("${ssh.default.port:#{null}}")
    private String port;

    @Value("${ssh.default.user:#{null}}")
    private String user;

    @Value("${ssh.default.password:#{null}}")
    private String password;

    public SshConnectionConfig getSshConnectionConfig() {
        return SshConnectionConfig.builder()
                .ip(ip)
                .password(password)
                .port(port)
                .user(user)
                .build();
    }

}
