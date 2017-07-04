package com.luolei.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : LuoLei
 * @Desc :
 * @Date : created at 2017/5/13 12:20
 * @Modified by:
 */
public class Application {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo_provider.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
