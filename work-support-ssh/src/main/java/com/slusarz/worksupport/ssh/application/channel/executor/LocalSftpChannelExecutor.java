package com.slusarz.worksupport.ssh.application.channel.executor;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.ssh.application.session.provider.SshSessionProvider;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Executor
public class LocalSftpChannelExecutor implements SftpChannelExecutor<SftpChannelResult> {

    private static final String CHANNEL_TYPE_SFTP = "sftp";

    @Provide
    private SshSessionProvider sshSessionProvider;

    @Override
    public SftpChannelResult execute(SftpChannelExecutable sftpChannelExecutable) {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sshSessionProvider.getSession();
            log.info("Try connect via ssh");
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel(CHANNEL_TYPE_SFTP);
            log.info("Try connect to sfth");
            channelSftp.connect();
            log.info("Execute: " + sftpChannelExecutable);
            return sftpChannelExecutable.execute(channelSftp);
        } catch (JSchException e) {
            log.info("Something go wrong.", e);
        } finally {
            log.info("Channel disconnect");
            Optional.ofNullable(channelSftp).ifPresent(ChannelSftp::disconnect);
            log.info("Session disconnect");
            Optional.ofNullable(session).ifPresent(Session::disconnect);
        }
        return null;
    }

}
