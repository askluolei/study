package com.luolei.cxfserver.start;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/7/4
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String name) {
        User user = new User();
        user.setName("xiaoming");
        user.setAge(22);
        return user;
    }
}
