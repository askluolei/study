package com.luolei.guice.ch6;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 绑定构造方法
 * 当例如 DatabaseTransactionLog 是一个第三方的jar包里面的类的时候  DatabaseConnection是我们自己的
 * 这时候 我们没办法在 DatabaseTransactionLog 中使用@Inject 注解 注入
 * 就可以通过构造方法来注入了
 * 但是要注意异常捕获
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                try {
                    bind(DatabaseTransactionLog.class).toConstructor(
                            DatabaseTransactionLog.class.getConstructor(DatabaseConnection.class));
                } catch (NoSuchMethodException e) {
                    addError(e);
                }
            }
        });

        DatabaseTransactionLog log = injector.getInstance(DatabaseTransactionLog.class);
        log.hello();
    }
}
