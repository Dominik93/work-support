package com.slusarz.worksupport.ssh.application.channel.executor;

import com.slusarz.worksupport.commontypes.application.executor.Execute;
import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@Slf4j
@Executor
public class SyncSftpChannelExecutor implements SftpChannelExecutor<Optional<SftpChannelResult>> {

    @Execute
    @Qualifier("localSftpChannelExecutor")
    private SftpChannelExecutor<SftpChannelResult> channelExecutor;

    public Optional<SftpChannelResult> execute(SftpChannelExecutable sftpChannelExecutable) {
        return Optional.ofNullable(channelExecutor.execute(sftpChannelExecutable));
    }

}
