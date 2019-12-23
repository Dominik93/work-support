package com.slusarz.worksupport.ssh.domain.session;

import com.jcraft.jsch.Session;

public interface SshSessionExecutable {

    SshSessionResult execute(Session session);


}
