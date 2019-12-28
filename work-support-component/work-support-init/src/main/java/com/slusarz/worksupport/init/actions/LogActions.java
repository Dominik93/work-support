package com.slusarz.worksupport.init.actions;

import lombok.Value;


@Value(staticConstructor = "of")
public class LogActions {

    private boolean liveLog;

    private boolean packageLiveLog;

    public static LogActions ofDefault() {
        return new LogActions(false, false);
    }

}
