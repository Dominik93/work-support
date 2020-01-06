package com.slusarz.worksupport.module.logdownloader.application.log;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.application.filter.RegularFileFilter;
import com.slusarz.worksupport.filemanager.application.remover.FileRemover;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.module.logdownloader.application.ApplicationProvider;
import com.slusarz.worksupport.module.logdownloader.application.mappers.FileToRemoveMapper;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.tenant.environment.configuration.EnvironmentConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LogScheduler {

    @Provide
    private LogDownloaderConfiguration logDownloaderConfiguration;

    @Provide
    private ApplicationProvider applicationProvider;

    @Provide
    private EnvironmentProvider environmentProvider;

    @Autowired
    private FileToRemoveMapper fileToRemoveMapper;

    @Autowired
    private RegularFileFilter regularFileFilter;

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    @Autowired
    @Qualifier("asyncFileRemoverService")
    private FileRemover fileRemover;

    //@Scheduled(cron = "0 0 5 * * *")
    @Scheduled(cron = "0 */10 * * * *")
    public void removeOldFiles() throws IOException {
        removeOldFiles(Paths.get(logDownloaderConfiguration.getOutputDirectory().getPath()), LocalDateTime.now().minusHours(1));
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void removeOldLogs() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(5);
        for (Environment environment : environmentConfiguration.getAvailableEnvironments()) {
            for (Application application : applicationProvider.getApplications()) {
                for (Server server : application.getServers()) {
                    removeOldFiles(Paths.get(logDownloaderConfiguration.getLogDirectory() + "/" + environment.getName() + "/" + server.getDir()), localDateTime);
                }
            }
        }
    }

    private void removeOldFiles(Path directoryPath, LocalDateTime date) throws IOException {
        List<IFile> files = prepareFileToRemove(directoryPath);
        log.info("Remove old files [" + files + "]");
        fileRemover.deleteFilesCreatedBefore(files, date);
    }


    public List<IFile> prepareFileToRemove(Path directoryPath) throws IOException {
        List<Path> pathStream = Files.walk(directoryPath).collect(Collectors.toList());
        List<Path> paths = regularFileFilter.filterPath(pathStream);
        return paths.stream().map(fileToRemoveMapper::toFileToRemove).collect(Collectors.toList());
    }

}
