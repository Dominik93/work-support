package com.slusarz.worksupport.module.logdownloader.specification;

import com.slusarz.worksupport.module.logdownloader.specification.model.DownloadLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.GetApplicationsResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping()
public interface LogDownloaderApi {

    @GetMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GetApplicationsResponse getApplications(HttpServletResponse response);

    @PostMapping(value = "/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void downloadLogs(DownloadLogRequest downloadLogRequest, HttpServletResponse response);

}
