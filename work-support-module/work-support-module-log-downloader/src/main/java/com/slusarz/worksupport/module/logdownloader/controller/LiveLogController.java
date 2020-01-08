package com.slusarz.worksupport.module.logdownloader.controller;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.logdownloader.application.ApplicationProvider;
import com.slusarz.worksupport.module.logdownloader.application.jirabug.JiraBugService;
import com.slusarz.worksupport.module.logdownloader.application.livelog.LiveLogService;
import com.slusarz.worksupport.module.logdownloader.application.log.LogDownloaderService;
import com.slusarz.worksupport.module.logdownloader.domain.LiveLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.domain.livelog.Token;
import com.slusarz.worksupport.module.logdownloader.specification.LiveLogApi;
import com.slusarz.worksupport.module.logdownloader.specification.model.InitLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.InitLiveLogResponse;
import com.slusarz.worksupport.module.logdownloader.specification.model.LiveLogEntry;
import com.slusarz.worksupport.module.logdownloader.specification.model.LiveLogResponse;
import com.slusarz.worksupport.module.logdownloader.specification.model.SaveLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.StopLiveLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.StopLiveLogResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LiveLogController implements LiveLogApi {

    @Autowired
    private JiraBugService jiraBugService;

    @Autowired
    private LogDownloaderService logDownloaderService;

    @Autowired
    private LiveLogService liveLogService;

    @Provide
    private ApplicationProvider applicationProvider;

    @Override
    public InitLiveLogResponse initLiveLog(@RequestBody InitLiveLogRequest initLiveLogRequest) {
        LiveLog liveLog = LiveLog.of(initLiveLogRequest.getApplications().stream()
                        .map(application -> applicationProvider.getApplication(ApplicationName.of(application)))
                        .collect(Collectors.toList()),
                initLiveLogRequest.getLogQuery().getQueries(),
                initLiveLogRequest.getLogQuery().isOnlyException(),
                initLiveLogRequest.getLogQuery().getBufferSize());
        Token token = liveLogService.startLiveLog(liveLog);
        return InitLiveLogResponse.builder().token(token.getToken()).build();
    }

    @Override
    public LiveLogResponse getLiveLog(@PathVariable("token") String token) {
        Map<Application, String> liveLog = liveLogService.getLiveLog(Token.of(token));
        List<LiveLogEntry> response = new ArrayList<>();

        for (Map.Entry<Application, String> entry : liveLog.entrySet()) {
            response.add(new LiveLogEntry(entry.getKey().getApplicationName().getName(), entry.getValue()));
        }

        return LiveLogResponse.builder()
                .liveLogEntries(response)
                .build();
    }

    @Override
    public StopLiveLogResponse stopLiveLog(@RequestBody StopLiveLogRequest request) {
        liveLogService.stopLiveLog(Token.of(request.getToken()));
        return StopLiveLogResponse.builder().success(true).build();
    }

    @Override
    public void saveLiveLog(@RequestBody SaveLiveLogRequest saveLiveLogRequest, HttpServletResponse response) {
        try {
            Map<Application, String> liveLog = new HashMap<>();
            for (LiveLogEntry liveLogEntry : saveLiveLogRequest.getLiveLogEntries()) {
                liveLog.put(applicationProvider.getApplication(ApplicationName.of(liveLogEntry.getApplication())),
                        liveLogEntry.getContent());
            }

            Archive archive = jiraBugService.createJiraBug(liveLog);
            File file = logDownloaderService.downloadLog(archive);
            setFile(file, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFile(File file, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        response.flushBuffer();
    }
}
