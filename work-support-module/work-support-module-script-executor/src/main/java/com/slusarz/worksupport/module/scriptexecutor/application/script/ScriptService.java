package com.slusarz.worksupport.module.scriptexecutor.application.script;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.module.scriptexecutor.application.exception.ReadFromFileRuntimeException;
import com.slusarz.worksupport.module.scriptexecutor.application.scriptexecutor.RunCommandPreparator;
import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptExecutorConfiguration;
import com.slusarz.worksupport.module.scriptexecutor.configuration.ScriptsConfiguration;
import com.slusarz.worksupport.module.scriptexecutor.configuration.config.ScriptConfig;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Option;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.Script;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptFile;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptName;
import com.slusarz.worksupport.module.scriptexecutor.domain.script.ScriptOutput;
import com.slusarz.worksupport.module.scriptexecutor.security.ScriptAuthorize;
import com.slusarz.worksupport.tenant.environment.application.context.EnvironmentTenantContext;
import com.slusarz.worksupport.tenant.environment.extension.TenantEnvironmentProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@ScriptAuthorize
public class ScriptService {

    @Autowired
    private ScriptExtensionExtractor scriptExtensionExtractor;

    @Provide
    private ScriptExecutorConfiguration scriptExecutorConfiguration;

    @Autowired
    private ScriptsConfiguration scriptsConfiguration;

    @Autowired
    private ScriptExecutorManager scriptExecutorManager;

    @Autowired
    private RunCommandPreparator runCommandPreparator;

    @Autowired
    private TenantEnvironmentProvider tenantEnvironmentProvider;

    public String runScript(final ScriptName scriptName, final List<Option> options) {
        Script script = getScript(scriptName);
        script.fillOptions(options);
        String fileName = getFileName(script);
        try {
            log.info(runCommandPreparator.prepareRunCommand(script));
            Files.createFile(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + fileName));
            scriptExecutorManager.get(script.getConnectionType()).execute(fileName, script);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private String getFileName(final Script script) {
        return String.join("-", String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)),
                EnvironmentTenantContext.getCurrentTenant().getName(),
                script.getScriptFile().getFile());
    }

    public ScriptOutput readFile(String token) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(scriptExecutorConfiguration.getDirectoryExecuted() + token));
            boolean allFetched = Files.exists(Paths.get((scriptExecutorConfiguration.getDirectoryExecuted() + token + ".done")));
            return ScriptOutput.of(new String(bytes, StandardCharsets.UTF_8), allFetched);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadFromFileRuntimeException(e);
        }
    }

    public List<Script> getScripts() {
        return scriptsConfiguration.getScripts().stream()
                .filter(scriptConfig -> scriptConfig.getEnvironments().contains(tenantEnvironmentProvider.provide()))
                .map(this::toScript).collect(Collectors.toList());

    }

    private Script toScript(ScriptConfig scriptConfig) {
        ScriptName scriptName = ScriptName.of(scriptConfig.getName());
        ScriptFile scriptFile = ScriptFile.of(scriptExecutorConfiguration.getDirectory(),
                scriptConfig.getScript(), scriptExtensionExtractor.extract(scriptName));
        return Script.of(scriptName,
                scriptFile,
                scriptConfig.getArguments(),
                scriptConfig.getOptions(),
                scriptConfig.getConnectionType(),
                scriptConfig.isMonitoring());
    }

    public Script getScript(ScriptName scriptName) {
        return getScripts().stream().filter(script -> script.getScriptName().equals(scriptName)).findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
