package com.luolei.guice.ch3;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 通过 bind(String.class).annotatedWith(Names.named("jdbc.url")).toInstance("jdbc:mysql://localhost:3306/test"); 可以直接绑定实例
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        JdbcProperties properties = injector.getInstance(JdbcProperties.class);
        System.out.println(properties.getUrl());
        System.out.println(properties.getUsername());
        System.out.println(properties.getPassword());
    }
}
