package com.slusarz.worksupport.ssh.application.channel.executor;

import com.slusarz.worksupport.commontypes.application.executor.Execute;
import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.Optional;
import java.util.concurrent.Future;

@Slf4j
@Executor
public class AsyncSftpChannelExecutor implements SftpChannelExecutor<Future> {

    @Execute
    @Qualifier("localSftpChannelExecutor")
    private SftpChannelExecutor<SftpChannelResult> channelExecutor;

    @Async
    public Future<Optional<SftpChannelResult>> execute(SftpChannelExecutable sftpChannelExecutable) {
        return new AsyncResult<>(Optional.ofNullable(channelExecutor.execute(sftpChannelExecutable)));
    }

}
