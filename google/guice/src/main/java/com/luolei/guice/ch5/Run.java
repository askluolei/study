package com.luolei.guice.ch5;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import javax.inject.Singleton;

/**
 * 对于具体类 可以不用绑定直接使用 也可以绑定 这样提前在容器中注册 而不是等到使用的时候注册
 * in 方法指定作用域 单例还是原型 默认是原型  也可以在具体类上加@Singleton注解来标志 这样就不用注册了
 * 也可以添加注解条件 注册的类和实现类都是自己
 *
 * 一个类可以注册多次
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(MyConcreteClass.class);
                bind(MyConcreteClass.class);
                bind(AnotherConcreteClass.class).in(Singleton.class);
                bind(MyConcreteClass.class).annotatedWith(Names.named("foo")).to(MyConcreteClass.class);
                bind(AnotherConcreteClass.class).annotatedWith(Names.named("foo")).to(AnotherConcreteClass.class).in(Singleton.class);
            }
        });

        MyConcreteClass concreteClass = injector.getInstance(MyConcreteClass.class);
        AnotherConcreteClass anotherConcreteClass = injector.getInstance(AnotherConcreteClass.class);

        concreteClass.hello();
        anotherConcreteClass.world();

        System.out.println(concreteClass == injector.getInstance(MyConcreteClass.class));
        System.out.println(anotherConcreteClass == injector.getInstance(AnotherConcreteClass.class));
    }

}
