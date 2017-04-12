package com.luolei.guice.ch4;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        //其他绑定
    }

    @Provides
    @Named("c3p0")
    DateSourceExample providerC3p0() {
        C3p0DataSource dataSource = new C3p0DataSource();
        //构造连接池 的其他必须条件
        return dataSource;
    }

    @Provides
    @Named("dbcp")
    DateSourceExample providerDbcp() {
        DBCPDataSource dataSource = new DBCPDataSource();
        //构造连接池 的其他必须条件
        return dataSource;
    }

    @Provides
    @Named("druid")
    DateSourceExample providerDruid() {
        DruidDataSource dataSource = new DruidDataSource();
        //构造连接池 的其他必须条件
        return dataSource;
    }
}
