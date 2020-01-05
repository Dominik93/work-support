package com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor;

import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptExecutorConfiguration;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@Executor
public class LocalScriptExecutor implements ScriptExecutor {

    @Provide
    private ScriptExecutorConfiguration scriptExecutorConfiguration;

    @Autowired
    private RunCommandPreparator runCommandPreparator;

    @Override
    public ConnectionType getConnection() {
        return ConnectionType.NONE;
    }

    @Async
    public void execute(String fileName, final Script script) {
        try {
            String runCommand = runCommandPreparator.prepareRunCommand(script);
            ProcessBuilder processBuilder = new ProcessBuilder()
                    .command(runCommand)
                    .directory(Paths.get(scriptExecutorConfiguration.getDirectory()).toFile());
            log.info("prepared script: " + runCommand);
            if (script.isMonitoring()) {
                Process process = processBuilder.start();
                Path path = Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName);
                InputStream in = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    Files.write(path, (line + "\n").getBytes(), StandardOpenOption.APPEND);
                    log.info(line);
                }
                Files.createFile(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName + ".done"));
            } else {
                new Thread(() -> {
                    try {
                        processBuilder.redirectErrorStream(true).start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).run();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
