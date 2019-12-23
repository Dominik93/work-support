package com.slusarz.worksupport.ssh.domain.channel;

import com.jcraft.jsch.ChannelSftp;

public interface SftpChannelExecutable {

    SftpChannelResult execute(ChannelSftp channelSftp);


}
