package com.slusarz.worksupport.ssh.application.session.provider;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.ssh.application.SshModuleRuntimeException;
import com.slusarz.worksupport.ssh.application.connection.SshConnectionPropertiesProvider;
import com.slusarz.worksupport.ssh.domain.conection.SshConnectionProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class SshSessionProvider {

    @Provide
    private SshConnectionPropertiesProvider sshConnectionPropertiesProvider;

    public Session getSession() {
        try {
            SshConnectionProperties sshConnectionProperties = sshConnectionPropertiesProvider.getSshConnectionProperties();
            log.info("Try prepare session: " + sshConnectionProperties);
            JSch jsch = new JSch();
            Session session;
            session = jsch.getSession(sshConnectionProperties.getUsername(),
                                      sshConnectionProperties.getIp(),
                                      sshConnectionProperties.getPort());
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(sshConnectionProperties.getPassword());
            log.info("Prepare session: " + sshConnectionProperties);
            return session;
        } catch (JSchException e) {
            throw new SshModuleRuntimeException();
        }
    }

}
