package com.luolei.jdk.jmx.notification;

/**
 * Describe : MBean 的实现类
 * Author : 罗雷
 * Date : 2017/6/9
 */
public class Hello implements HelloMBean {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printHello() {
        System.out.println("Hello : " + name);
    }

    @Override
    public void printHello(String whoName) {
        System.out.println("Hello : " + whoName);
    }
}
