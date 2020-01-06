package com.slusarz.worksupport.module.logdownloader.configuration;

import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@ToString
@Getter
@Configuration
public class LogDownloaderConfiguration {

    @Value("${log-downloader.dir}")
    private DirectoryPath logDirectory;

    @Value("${log-downloader.output.dir}")
    private DirectoryPath outputDirectory;

}
