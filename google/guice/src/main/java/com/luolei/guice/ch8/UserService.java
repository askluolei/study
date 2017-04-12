package com.luolei.guice.ch8;

import com.google.inject.ProvidedBy;

/**
 * Created by 罗雷 on 2017/4/12.
 */
@ProvidedBy(UserServiceProvider.class)
public class UserService {

    public void world() {
        System.out.println("world");
    }
}
