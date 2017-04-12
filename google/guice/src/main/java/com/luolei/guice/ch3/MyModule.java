package com.luolei.guice.ch3;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("jdbc.url")).toInstance("jdbc:mysql://localhost:3306/test");
        bind(String.class).annotatedWith(Names.named("jdbc.username")).toInstance("root");
        bind(String.class).annotatedWith(Names.named("jdbc.password")).toInstance("123456");
    }
}
