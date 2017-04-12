package com.luolei.guice.ch7;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class MyLog {

    @Inject
    private Logger logger;

    public void logConnectException(String msg) {
    /* the message is logged to the "ConsoleTransacitonLog" logger */
        logger.warning("Connect exception failed, " + msg);
    }
}
