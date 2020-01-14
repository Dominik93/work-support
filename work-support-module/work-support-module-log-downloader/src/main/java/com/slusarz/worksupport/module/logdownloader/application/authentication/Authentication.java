package com.slusarz.worksupport.module.logdownloader.application.authentication;

import com.slusarz.worksupport.commontypes.application.provider.annotation.Provide;
import com.slusarz.worksupport.commontypes.extension.environment.EnvironmentProvider;
import com.slusarz.worksupport.module.logdownloader.configuration.config.ApplicationsConfiguration;
import com.slusarz.worksupport.module.logdownloader.configuration.config.EnvironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Component
public class Authentication {

    @Autowired
    private ApplicationsConfiguration applicationsConfiguration;

    @Provide
    private EnvironmentProvider environmentProvider;

    public void authenticate() {
        EnvironmentConfig environment = applicationsConfiguration.getEnvironment(environmentProvider.provide());
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(environment.getAuthentication().getUsername(), environment.getAuthentication().getPassword().toCharArray());
            }
        });
    }
}
