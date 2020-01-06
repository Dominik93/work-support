package com.slusarz.worksupport.module.logdownloader.configuration;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.filemanager.application.creator.FileCreatorService;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationConfig;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationsConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.EnvironmentConfig;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.file.DirectoryPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Configuration
public class LogDownloaderInitializator {

    @Autowired
    private FileCreatorService fileCreatorService;

    @Autowired
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @Provide
    private ApplicationsConfiguration applicationsConfiguration;

    @PostConstruct
    public void init() throws IOException {
        fileCreatorService.createDirectoryIfNotExist(logDownloaderConfiguration.getLogDirectory());
        fileCreatorService.createDirectoryIfNotExist(logDownloaderConfiguration.getOutputDirectory());
        createEnvironmentDirectories();
        createServerDirectories();
    }

    private void createEnvironmentDirectories() throws IOException {
        for (EnvironmentConfig environment : applicationsConfiguration.getEnvironments()) {
            DirectoryPath directoryPath
                    = DirectoryPathFactory.of(logDownloaderConfiguration.getLogDirectory().getPath(), environment.getEnvironment().getName());
            fileCreatorService.createDirectoryIfNotExist(directoryPath);

        }
    }

    private void createServerDirectories() throws IOException {
        for (ApplicationConfig application : applicationsConfiguration.getApplications()) {
            for (Environment environment : application.getEnvironments()) {
                for (Server server : applicationsConfiguration.getEnvironment(environment).getServers()) {
                    DirectoryPath directoryPath
                            = DirectoryPathFactory.of(logDownloaderConfiguration.getLogDirectory().getPath(), environment.getName(), server.getDir());
                    fileCreatorService.createDirectoryIfNotExist(directoryPath);
                }
            }
        }
    }

}
