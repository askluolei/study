package com.luolei.guice.ch4;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class C3p0DataSource implements DateSourceExample {

    @Override
    public String getConnection() {
        return "c3p0";
    }
}
