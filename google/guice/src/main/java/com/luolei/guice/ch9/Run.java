package com.luolei.guice.ch9;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;

import java.time.LocalDate;

/**
 * AOP 功能
 * 绑定拦截器
 * bindInterceptor  第一个参数是匹配的类 第二个参数是匹配的方法 后面的参数是拦截器
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(HelloService.class).to(HelloServiceImpl.class).in(Scopes.SINGLETON);
                bindInterceptor(Matchers.any(), Matchers.annotatedWith(NotOnWeekends.class), new WeekendBlocker());
            }
        });

        HelloService service = injector.getInstance(HelloService.class);
        LocalDate date = LocalDate.now();
        for (int i = 1; i <= 7; i++) {
            try {
                service.hello(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            date = date.plusDays(1);
        }
    }
}
