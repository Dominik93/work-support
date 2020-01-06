package com.slusarz.worksupport.module.logdownloader.specification;

import com.slusarz.worksupport.module.logdownloader.specification.model.DownloadLogRequest;
import com.slusarz.worksupport.module.logdownloader.specification.model.GetApplicationsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/log-downloader")
public interface LogDownloaderApi {

    @GetMapping("/applications")
    GetApplicationsResponse getApplications(HttpServletResponse response);

    @PostMapping("/download")
    void downloadLogs(DownloadLogRequest downloadLogRequest, HttpServletResponse response);

}
