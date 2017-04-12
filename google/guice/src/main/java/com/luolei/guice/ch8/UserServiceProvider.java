package com.luolei.guice.ch8;

import com.google.inject.Provider;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class UserServiceProvider implements Provider<UserService> {
    @Override
    public UserService get() {
        return new UserService();
    }
}
