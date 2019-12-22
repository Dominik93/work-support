package com.slusarz.worksupport.init.actions;

import lombok.Value;


@Value(staticConstructor = "of")
public class LogActions {

    private boolean liveLog;

    private boolean jiraBug;

}
