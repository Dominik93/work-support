package com.slusarz.worksupport.module.scriptexecutor.application.exception;

import com.jcraft.jsch.Session;

public class NoSessionRuntimeException extends RuntimeException {

    public NoSessionRuntimeException(Session session) {
        super(String.format("No session for host %s@%s:%s",
                session.getUserName(),
                session.getHost(),
                session.getPort()));
    }


}
