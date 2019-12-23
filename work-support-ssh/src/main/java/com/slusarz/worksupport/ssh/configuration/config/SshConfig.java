package com.slusarz.worksupport.ssh.configuration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties("ssh")
public class SshConfig {

    private List<SshConnectionConfig> connections;

}
