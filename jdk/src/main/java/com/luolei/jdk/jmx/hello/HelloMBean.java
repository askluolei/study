package com.luolei.jdk.jmx.hello;

/**
 * Created by 罗雷 on 2017/6/9.
 */
public interface HelloMBean {

    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();
}
