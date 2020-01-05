package com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.slusarz.worksupport.commontypes.application.executor.Executor;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.scriptexecutor.application.exception.NoSessionRuntimeException;
import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptExecutorConfiguration;
import com.slusarz.worksupport.module.scriptexecutor.domain.exceptions.ScriptModuleRuntimeException;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ConnectionType;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.ssh.application.session.provider.SshSessionProvider;
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
public class SshScriptExecutor implements ScriptExecutor {

    @Provide
    private ScriptExecutorConfiguration scriptExecutorConfiguration;

    @Provide
    private SshSessionProvider sshSessionProvider;

    @Autowired
    private RunCommandPreparator runCommandPreparator;

    @Override
    public ConnectionType getConnection() {
        return ConnectionType.SSH;
    }

    @Async
    public void execute(String fileName, final Script script) {
        Session session = sshSessionProvider.getSession();
        if (!session.isConnected()) {
            throw new NoSessionRuntimeException(session);
        }
        ChannelExec channelExec;

        try {
            channelExec = (ChannelExec) session.openChannel("exec");
            // Set the Command to execute on the channel and execute the CommandExecutor
            channelExec.setCommand(runCommandPreparator.prepareRunCommand(script));
            channelExec.connect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }

        // Get an InputStream from this channel and read messages, generated
        // by the executing CommandExecutor, from the remote side.
        try {
            Path path = Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName);
            InputStream in = channelExec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
                log.info(line);
            }
            // Command execution completed here.

            Files.createFile(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName + ".done"));
            // Retrieve the exit status of the executed CommandExecutor
            int exitStatus = channelExec.getExitStatus();
            if (exitStatus > 0) {
                log.info("Remote script exec error! " + exitStatus);
            }
        } catch (IOException e) {
            throw new ScriptModuleRuntimeException(e);
        }
    }

}
