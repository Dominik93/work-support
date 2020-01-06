package com.slusarz.worksupport.module.logdownloader.domain.exceptions;

public class LogModuleRuntimeException extends RuntimeException {
    public LogModuleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogModuleRuntimeException(Throwable cause) {
        super(cause);
    }

    public LogModuleRuntimeException(String message) {
        super(message);
    }
}
