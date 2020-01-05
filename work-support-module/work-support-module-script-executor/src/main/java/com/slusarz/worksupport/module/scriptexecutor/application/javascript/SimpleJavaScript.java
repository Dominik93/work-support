package com.slusarz.worksupport.module.scriptexecutor.application.javascript;

import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptExecutorConfiguration;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


@Slf4j
@Component
public class SimpleJavaScript implements JavaScript {

    @Autowired
    private ScriptExecutorConfiguration scriptExecutorConfiguration;

    @Override
    public ScriptName getName() {
        return ScriptName.of("Test script");
    }

    @Override
    public void run(String fileName, Script script) {
        try {
            Path path = Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName);
            Files.write(path, script.toString().getBytes());
            for (int i = 0; i < 200; i++) {
                Thread.sleep(500);
                log.info("After sleep, write to file " + i);
                Files.write(path, ("iteration number " + i + "\r\n").getBytes(), StandardOpenOption.APPEND);
            }
            Files.createFile(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName + ".done"));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
