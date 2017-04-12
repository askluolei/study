package com.luolei.guice.ch7;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

/**
 * Guice 里面内置的绑定 譬如 java.util.Logger
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        MyLog log = injector.getInstance(MyLog.class);
        log.logConnectException("Hello World!");
    }
}
