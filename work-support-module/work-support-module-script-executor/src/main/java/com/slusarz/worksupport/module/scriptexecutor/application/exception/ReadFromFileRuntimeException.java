package com.slusarz.worksupport.module.scriptexecutor.application.exception;

import java.io.IOException;

public class ReadFromFileRuntimeException extends RuntimeException {

    public ReadFromFileRuntimeException(IOException e) {
        super(e);
    }

}
