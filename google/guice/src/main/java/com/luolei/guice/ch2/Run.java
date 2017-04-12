package com.luolei.guice.ch2;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 针对接口编程的 需要编写Module类来绑定实现类
 * 接口有多个实现的可以通过内置的Names 来绑定 使用注入时候 根据@Named注解上的名称来确定
 * 或者自定义注解
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());

        UserService service = injector.getInstance(UserService.class);
        service.helloUser();

    }
}
