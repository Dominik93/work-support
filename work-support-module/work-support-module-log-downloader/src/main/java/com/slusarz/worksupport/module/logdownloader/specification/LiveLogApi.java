package com.slusarz.worksupport.module.logdownloader.specification;

import com.slusarz.worksupport.module.logdownloader.specification.model.InitLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.InitLiveLogResponse;
import com.slusarz.worksupport.module.logdownloader.specification.model.LiveLogResponse;
import com.slusarz.worksupport.module.logdownloader.specification.model.SaveLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.StopLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.StopLiveLogResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/live")
public interface LiveLogApi {

    @PostMapping("/start")
    InitLiveLogResponse initLiveLog(InitLiveLogRequest initLiveLogRequest);

    @GetMapping("/{token}")
    LiveLogResponse getLiveLog(String token);

    @PostMapping("/stop")
    StopLiveLogResponse stopLiveLog(StopLiveLogRequest token);

    @PostMapping("/save")
    void saveLiveLog(SaveLiveLogRequest saveLiveLogRequest, HttpServletResponse response);

}
