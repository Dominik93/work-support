package com.slusarz.worksupport.module.logdownloader.domain;

import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.Value;

@Value(staticConstructor = "of")
public class CurrentFileLogChannelResult implements SftpChannelResult {

    private CurrentLogFile currentLogFile;

}
