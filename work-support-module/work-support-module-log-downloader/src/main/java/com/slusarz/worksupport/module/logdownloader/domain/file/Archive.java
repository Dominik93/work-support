package com.slusarz.worksupport.module.logdownloader.domain.file;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.filemanager.domain.file.FileName;
import com.slusarz.worksupport.filemanager.domain.file.IFile;
import com.slusarz.worksupport.filemanager.domain.path.DirectoryPath;
import com.slusarz.worksupport.module.logdownloader.configuration.LogDownloaderConfiguration;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value(staticConstructor = "of")
public class Archive implements IFile {

    private static final String ARCHIVE_PATTERN = "yyyy-MM-dd-HH-mm-ss";

    private DirectoryPath directoryPath;

    private FileName name;

    public static Archive of(final LogDownloaderConfiguration logDownloaderConfiguration) {
        return new Archive(logDownloaderConfiguration.getOutputDirectory(),
                FileName.of(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ARCHIVE_PATTERN)) + ".zip"));
    }

    public static Archive of(final LogDownloaderConfiguration logDownloaderConfiguration, final Environment environment) {
        return new Archive(logDownloaderConfiguration.getOutputDirectory(),
                FileName.of(environment.getName() + "_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern(ARCHIVE_PATTERN)) + ".zip"));
    }

}
