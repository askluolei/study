package com.luolei.jdk.jmx.notification;

/**
 * Describe : 注册的MBean 必须先定义接口 接口名必须以MBean结尾
 * Author : 罗雷
 * Date : 2017/6/9
 */
public interface HelloMBean {

    String getName();

    void setName(String name);

    void printHello();

    void printHello(String whoName);
}
