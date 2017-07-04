package com.luolei.cxfserver.start;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/7/4
 */
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHi(String text) {
        return "Hello " + text;
    }
}
