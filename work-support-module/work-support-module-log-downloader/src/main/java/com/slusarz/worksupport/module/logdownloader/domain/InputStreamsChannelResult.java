package com.slusarz.worksupport.module.logdownloader.domain;

import com.slusarz.worksupport.module.logdownloader.domain.file.CurrentLogFile;
import com.slusarz.worksupport.ssh.domain.channel.SftpChannelResult;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class InputStreamsChannelResult implements SftpChannelResult {

    private List<CurrentLogFile> currentLogFiles;

}
