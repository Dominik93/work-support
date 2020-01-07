package com.slusarz.worksupport.init.actions;

import lombok.Value;


@Value(staticConstructor = "of")
public class LogDownloaderActions {

    private boolean liveLog;

    private boolean packageLiveLog;

    public static LogDownloaderActions ofDefault() {
        return new LogDownloaderActions(false, false);
    }

}
