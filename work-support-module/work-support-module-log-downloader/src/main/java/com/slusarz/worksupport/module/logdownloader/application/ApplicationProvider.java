package com.slusarz.worksupport.module.logdownloader.application;

import com.slusarz.worksupport.commontypes.application.DefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.SingleDefaultPropertiesHelper;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.application.provider.annotation.Provider;
import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationConfig;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationsConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.DefaultLogFileConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.EnvironmentConfig;
import com.slusarz.worksupport.module.logdownloader.configuration.config.LogFileConfig;
import com.slusarz.worksupport.module.logdownloader.configuration.exceptions.ApplicationNotFound;
import com.slusarz.worksupport.module.logdownloader.domain.application.Application;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationFileName;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationPath;
import com.slusarz.worksupport.module.logdownloader.domain.application.ConcatenationCharacter;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class ApplicationProvider {

    @Autowired
    private DefaultLogFileConfiguration defaultLogFileConfiguration;

    @Provide
    private EnvironmentProvider environmentProvider;

    @Autowired
    private ApplicationsConfiguration applicationsConfiguration;

    private Map<Environment, List<Application>> applications;

    @PostConstruct
    public void init() {
        applications = new HashMap<>();
        applicationsConfiguration.getApplications().stream()
                .map(ApplicationConfig::getEnvironments)
                .flatMap(Collection::stream)
                .distinct()
                .forEach(environment -> applications.put(environment, new ArrayList<>()));
        for (ApplicationConfig application : applicationsConfiguration.getApplications()) {
            for (Environment environment : application.getEnvironments()) {
                    applications.get(environment).add(toApplication(application, applicationsConfiguration.getEnvironment(environment)));
            }
        }
    }

    private Application toApplication(ApplicationConfig application, EnvironmentConfig environment) {
        DefaultPropertiesHelper<LogFileConfig, ApplicationFileName> applicationFileNameDefaultPropertiesHelper
                = new SingleDefaultPropertiesHelper<>(defaultLogFileConfiguration.getDefault(), application.getLogFile());
        DefaultPropertiesHelper<LogFileConfig, String> dateFormatDefaultPropertiesHelper
                = new SingleDefaultPropertiesHelper<>(defaultLogFileConfiguration.getDefault(), application.getLogFile());
        DefaultPropertiesHelper<LogFileConfig, ConcatenationCharacter> concatenationCharacterDefaultPropertiesHelper
                = new SingleDefaultPropertiesHelper<>(defaultLogFileConfiguration.getDefault(), application.getLogFile());
        DefaultPropertiesHelper<LogFileConfig, Format> formatDefaultPropertiesHelper
                = new SingleDefaultPropertiesHelper<>(defaultLogFileConfiguration.getDefault(), application.getLogFile());
        return Application.of(application.getName(),
                applicationFileNameDefaultPropertiesHelper.getOrDefault(LogFileConfig::getFileName),
                ApplicationPath.of(environment.getPath()),
                application.getTag(),
                environment.getServers(),
                DateTimeFormatter.ofPattern(dateFormatDefaultPropertiesHelper.getOrDefault(LogFileConfig::getDateFormat)),
                formatDefaultPropertiesHelper.getOrDefault(LogFileConfig::getLogFormat),
                formatDefaultPropertiesHelper.getOrDefault(LogFileConfig::getCompressFormat),
                concatenationCharacterDefaultPropertiesHelper.getOrDefault(LogFileConfig::getConcatCharacter));
    }


    public List<Application> getApplications() {
        return applications.getOrDefault(environmentProvider.provide(), Collections.emptyList());
    }

    public Application getApplication(ApplicationName applicationName) {
        return applications.get(environmentProvider.provide())
                .stream()
                .filter(application -> application.getApplicationName().equals(applicationName))
                .findFirst()
                .orElseThrow(() -> new ApplicationNotFound(applicationName));
    }

}
