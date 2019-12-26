package com.slusarz.worksupport.ssh.application.channel.executor;

import com.slusarz.worksupport.ssh.domain.channel.SftpChannelExecutable;

public interface SftpChannelExecutor<T> {

    T execute(SftpChannelExecutable sftpChannelExecutable);

}
