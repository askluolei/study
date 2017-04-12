package com.luolei.guice.ch8;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 还有几个奇怪的注解 在接口上注解 @ImplementedBy 指明实现类 这样就不用在module里面注册绑定了
 * 注解 @ProvidedBy 也是一样的作用
 *
 * 不过尽量不要这样用
 *
 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        UserDao dao = injector.getInstance(UserDao.class);
        dao.hello();

        UserService service = injector.getInstance(UserService.class);
        service.world();
    }
}
