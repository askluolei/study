package com.luolei.guice.ch1;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

/**
 * 非接口的直接从容器中获取
 * 不要忽视Stage， 即 PRODUCTION(生产环境) 和 DEVELOPMENT(开发环境)对bean的实例化规则.前者会在容器初始化时, 实例化所有的bean. 后者会在使用到该bean时, 才实例化.
 所以, 生产环境没必要用lazy init, 大胆选用 PRODUCTION.

 * Created by 罗雷 on 2017/4/12.
 */
public class Run {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT);
        UserService service = injector.getInstance(UserService.class);
        service.helloUser();
    }
}
