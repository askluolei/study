package com.luolei.guice.ch4;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 对应构建复杂对象并加入容器管理 可以在module类里面编写方法  返回值就是要加入的bean
 * 方法要@Providers 注解标识
 * 如果也用@Named 注解标记了 在注入的时候也会根据@Named注解注入
 * 也可以用自定义注解 代替@Named
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        MyService myService = injector.getInstance(MyService.class);
        myService.test();
    }
}
