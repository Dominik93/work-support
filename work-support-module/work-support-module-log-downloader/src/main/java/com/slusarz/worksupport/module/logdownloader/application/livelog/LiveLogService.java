package com.slusarz.worksupport.module.logdownloader.application.livelog;

import com.slusarz.worksupport.module.logdownloader.domain.LiveLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.livelog.Token;
import com.slusarz.worksupport.module.logdownloader.security.LiveLogAuthorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@LiveLogAuthorize
public class LiveLogService {

    @Autowired
    private LiveLoggerMap liveLoggerMap;

    public Token startLiveLog(LiveLog liveLog) {
        Token token = Token.random();
        log.info("Generated token: " + token);
        liveLoggerMap.start(token, liveLog);
        return token;
    }

    public Map<Application, String> getLiveLog(Token token) {
        Map<Application, String> applicationStringMap = liveLoggerMap.get(token);
        log.info("live log: " + applicationStringMap);
        return applicationStringMap;
    }

    public void stopLiveLog(Token token) {
        liveLoggerMap.stop(token);
    }
}
