package com.luolei.guice.ch1;

import javax.inject.Inject;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class UserService {

    @Inject
    private UserDao dao;

    public void helloUser() {
        System.out.println("UserService -> helloUser");
        dao.printUser();
    }
}
