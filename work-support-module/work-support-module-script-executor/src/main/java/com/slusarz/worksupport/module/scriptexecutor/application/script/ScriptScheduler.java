package com.slusarz.worksupport.module.scriptexecutor.application.script;

import com.slusarz.worksupport.filemanager.application.remover.FileRemover;
import com.slusarz.worksupport.filemanager.domain.directorypath.DirectoryPath;
import com.slusarz.worksupport.filemanager.domain.filename.FileName;
import com.slusarz.worksupport.filemanager.domain.remove.FileToRemove;
import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptExecutorConfiguration;
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
import java.util.stream.Stream;

@Slf4j
@Component
public class ScriptScheduler {

    @Autowired
    private ScriptExecutorConfiguration scriptExecutorConfiguration;

    @Autowired
    @Qualifier("asyncFileRemoverService")
    private FileRemover fileRemover;

    //@Scheduled(cron = "0 0 5 * * *")
    @Scheduled(cron = "0 */10 * * * *")
    public void removeOldFiles() throws IOException {
        Stream<Path> pathStream = Files.walk(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted()));
        List<FileToRemove> fileToRemoves = collectFilesToRemove(pathStream);
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        fileRemover.deleteFilesCreatedBefore(fileToRemoves, localDateTime);
    }

    private List<FileToRemove> collectFilesToRemove(Stream<Path> pathStream) {
        return pathStream.filter(Files::isRegularFile)
                .map(this::toFileToRemove)
                .collect(Collectors.toList());
    }

    private FileToRemove toFileToRemove(Path path) {
        FileName name = FileName.of(path.getFileName().toString());
        DirectoryPath directoryPath = DirectoryPath.of(path.getParent().toString());
        return FileToRemove.of(name, directoryPath);
    }
}
