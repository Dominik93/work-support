package com.slusarz.worksupport.ssh.application.session.executor;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.ssh.application.session.provider.SshSessionProvider;
import com.slusarz.worksupport.ssh.domain.session.SshSessionExecutable;
import com.slusarz.worksupport.ssh.domain.session.SshSessionResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Executor
public class SshSessionExecutor {

    @Provide
    private SshSessionProvider sshSessionProvider;

    public Optional<SshSessionResult> execute(SshSessionExecutable sshSessionExecutable) {
        Session session = sshSessionProvider.getSession();
        try {
            log.info("Try connect via ssh");
            session.connect();
            log.info("Execute: " + sshSessionExecutable);
            SshSessionResult result = sshSessionExecutable.execute(session);
            log.info("Session disconnect");
            session.disconnect();
            return Optional.ofNullable(result);
        } catch (JSchException e) {
            log.info("Something go wrong.", e);
        }
        return Optional.empty();
    }

}
