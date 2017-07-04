package com.luolei.dubbo.provider.service.impl;

import com.luolei.dubbo.service.DemoService;

/**
 * @Author : LuoLei
 * @Desc :
 * @Date : created at 2017/5/13 12:19
 * @Modified by:
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
