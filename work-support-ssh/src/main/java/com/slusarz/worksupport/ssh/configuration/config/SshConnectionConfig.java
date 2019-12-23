package com.slusarz.worksupport.ssh.configuration.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
public class SshConnectionConfig {

    private String environment;

    private String ip;

    private String port;

    private String user;

    private String password;

}
