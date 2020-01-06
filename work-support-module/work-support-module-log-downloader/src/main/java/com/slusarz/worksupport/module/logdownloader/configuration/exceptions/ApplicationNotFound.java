package com.slusarz.worksupport.module.logdownloader.configuration.exceptions;

import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;

public class ApplicationNotFound extends RuntimeException {
    public ApplicationNotFound(ApplicationName applicationName) {
        super("No application with name " + applicationName);
    }
}