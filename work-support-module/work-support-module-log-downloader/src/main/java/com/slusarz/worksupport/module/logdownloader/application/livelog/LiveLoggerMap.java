package com.slusarz.worksupport.module.logdownloader.application.livelog;

import com.slusarz.worksupport.commontypes.application.executor.Execute;
import com.slusarz.worksupport.module.logdownloader.domain.LiveLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.livelog.Token;
import com.slusarz.worksupport.ssh.application.channel.executor.SftpChannelExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Slf4j
@Component
public class LiveLoggerMap {

    @Execute
    @Qualifier("asyncSftpChannelExecutor")
    private SftpChannelExecutor<Future> sftpChannelExecutor;

    private Map<Token, LiveLogger> liveLoggers = new HashMap<>();

    public Map<Application, String> get(Token token) {
        log.info("get " + token);
        return liveLoggers.get(token).popContent();
    }

    public void start(Token token, LiveLog liveLog) {
        log.info("start " + token);
        LiveLogger value = new LiveLogger(liveLog);
        sftpChannelExecutor.execute(value);
        liveLoggers.put(token, value);
    }

    public void stop(Token token) {
        log.info("stop " + token);
        liveLoggers.get(token).end();
        liveLoggers.remove(token);
    }
}
