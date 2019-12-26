package com.slusarz.worksupport.ssh.domain.conection;

import lombok.Value;

@Value(staticConstructor = "of")
public class SshConnectionProperties {

    private String username;

    private String password;

    private String ip;

    private Integer port;

}
