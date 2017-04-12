package com.luolei.guice.ch2;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).annotatedWith(Names.named("Cn")).to(UserDaoImplCn.class);
        bind(UserDao.class).annotatedWith(Names.named("En")).to(UserDaoImplEn.class);
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
