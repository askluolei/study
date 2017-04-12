package com.luolei.guice.ch4;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class MyService {

    @Named("dbcp")
    @Inject
    private DateSourceExample dataSource;

    public void test() {
        System.out.println(dataSource.getConnection());
    }
}
