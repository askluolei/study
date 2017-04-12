package com.luolei.guice.ch8;

import com.google.inject.ImplementedBy;

/**
 * Created by 罗雷 on 2017/4/12.
 */
@ImplementedBy(UserDaoImpl.class)
public interface UserDao {
    void hello();
}
