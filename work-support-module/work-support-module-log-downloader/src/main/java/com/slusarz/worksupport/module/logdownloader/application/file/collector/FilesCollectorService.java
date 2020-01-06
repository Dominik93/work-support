package com.slusarz.worksupport.module.logdownloader.application.file.collector;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import com.slusarz.worksupport.module.logdownloader.domain.DownloadLog;
import com.slusarz.worksupport.module.logdownloader.domain.Server;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.file.FileToZip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FilesCollectorService {

    @Provide
    private EnvironmentProvider environmentProvider;

    @Provide
    private LogDownloaderConfiguration logDownloaderConfiguration;

    public List<FileToZip> collectFiles(final DownloadLog downloadLog) {
        Environment environment = environmentProvider.provide();
        List<FileToZip> paths = new ArrayList<>();
        for (Application application : downloadLog.getApplications()) {
            for (Server server : application.getServers()) {
                for (LocalDateTime localDateTime : downloadLog.getDates()) {
                    FileToZip fileToZip = FileToZip.of(getDirectoryPath(environment,server), getFileName(application, localDateTime), server);
                    log.info("Get file [" + fileToZip + "]");
                    paths.add(fileToZip);
                }
            }
        }
        return paths;
    }

    private DirectoryPath getDirectoryPath(Environment environment, Server server) {
        return DirectoryPath.of(logDownloaderConfiguration.getLogDirectory() + "/" + environment + "/" + server.getDir());

    }

    private FileName getFileName(Application application, LocalDateTime localDateTime) {
        return FileName.of(application.getApplicationFileName().getName() + application.getLogFormat().getFormat() +
                application.getConcatenationCharacter().getCharacter() +
                localDateTime.format(application.getDateTimeFormatter()));

    }
    private Path getLocalPath(Environment environment, Server server, Application application, LocalDateTime localDateTime) {
        return Paths.get(logDownloaderConfiguration.getLogDirectory() + "/" + environment + "/" + server.getDir() + "/" +
                application.getApplicationFileName().getName() + application.getLogFormat().getFormat() +
                application.getConcatenationCharacter().getCharacter() +
                localDateTime.format(application.getDateTimeFormatter()));

    }

}
