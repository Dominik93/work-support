package com.slusarz.worksupport.module.logdownloader.application.downloader;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.module.logdownloader.application.file.FileDownloader;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationsConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.EnvironmentConfig;
import com.slusarz.worksupport.module.logdownloader.domain.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DownloaderManager {

    private Map<Connection, FileDownloader>  downloaderMap = new HashMap<>();

    @Provide
    private EnvironmentProvider environmentProvider;

    @Autowired
    private ApplicationsConfiguration applicationsConfiguration;

    @Autowired
    private List<FileDownloader> fileDownloaders;

    @Provide
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @PostConstruct
    public void init() {
        for (FileDownloader fileDownloader : fileDownloaders) {
            downloaderMap.put(fileDownloader.getConnection(), fileDownloader);
        }
    }

    public FileDownloader get() {
        EnvironmentConfig environment = applicationsConfiguration.getEnvironment(environmentProvider.provide());
        return downloaderMap.get(environment.getConnection());
    }
}
