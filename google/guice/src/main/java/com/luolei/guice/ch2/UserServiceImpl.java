package com.luolei.guice.ch2;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class UserServiceImpl implements UserService {

    @Inject
    @Named("En")
    private UserDao dao;

    @Override
    public void helloUser() {
        System.out.println("service");
        dao.printUser();
        System.out.println(dao);
    }
}
