package com.slusarz.worksupport.module.logdownloader.controller;


import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.logdownloader.application.ApplicationProvider;
import com.slusarz.worksupport.module.logdownloader.application.jirabug.JiraBugService;
import com.slusarz.worksupport.module.logdownloader.application.log.LogDownloaderService;
import com.slusarz.worksupport.module.logdownloader.application.log.preparators.LogPreparator;
import com.slusarz.worksupport.module.logdownloader.controller.mapper.ApplicationMapper;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;
import com.slusarz.worksupport.module.logdownloader.domain.file.Archive;
import com.slusarz.worksupport.module.logdownloader.specification.LogDownloaderApi;
import com.slusarz.worksupport.module.logdownloader.specification.model.DownloadLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.GetApplicationsResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class LogDownloaderController implements LogDownloaderApi {

    @Autowired
    @Qualifier("logPreparatorService")
    private LogPreparator logPreparator;

    @Autowired
    @Qualifier("currentLogPreparatorService")
    private LogPreparator currentLogPreparator;

    @Autowired
    private LogDownloaderService logDownloaderService;

    @Autowired
    private JiraBugService jiraBugService;

    @Provide
    private ApplicationProvider applicationProvider;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public GetApplicationsResponse getApplications(HttpServletResponse response) {
        return applicationMapper.toApplicationsResponse(applicationProvider.getApplications());
    }

    @Override
    public void downloadLogs(@RequestBody DownloadLogRequest downloadLogRequest, HttpServletResponse response) {
        DownloadLog downloadLog = DownloadLog.of(
                downloadLogRequest.getApplications().stream()
                        .map(application -> applicationProvider.getApplication(ApplicationName.of(application)))
                        .collect(Collectors.toList()),
                downloadLogRequest.getDate(),
                downloadLogRequest.getFrom(),
                downloadLogRequest.getTo(),
                downloadLogRequest.getLogQuery().isOnlyException(),
                downloadLogRequest.getLogQuery().getBufferSize(),
                downloadLogRequest.getLogQuery().getQueries());

        try {
            Archive archive;
            if (downloadLogRequest.isCurrent()) {
                archive = currentLogPreparator.prepareLog(downloadLog);
            } else {
                archive = logPreparator.prepareLog(downloadLog);
            }
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
