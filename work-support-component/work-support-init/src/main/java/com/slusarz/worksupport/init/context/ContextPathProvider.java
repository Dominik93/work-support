package com.slusarz.worksupport.init.context;

import com.slusarz.worksupport.init.Constants;

public class ContextPathProvider {

    public static String path(String service) {
        return String.format(Constants.CONTEXT_INIT_PATH, service);
    }

}
