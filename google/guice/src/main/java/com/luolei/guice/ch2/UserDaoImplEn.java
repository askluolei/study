package com.luolei.guice.ch2;

import javax.inject.Singleton;

/**
 * Created by 罗雷 on 2017/4/12.
 */
@Singleton
public class UserDaoImplEn implements UserDao {
    @Override
    public void printUser() {
        System.out.println("UserDaoImplEn -> printUser");
    }
}
