package com.luolei.jdk.reflect.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/29
 */
public class Runner {

    public static void main(String[] args) throws Exception{
//        Field fieldA = B.class.getDeclaredField("a");
        Field fieldB = B.class.getDeclaredField("b");
//        System.out.println(Objects.isNull(fieldA));
        System.out.println(Objects.isNull(fieldB));
        Method methodA = B.class.getDeclaredMethod("setA", String.class);
        Method methodB = B.class.getDeclaredMethod("setB", String.class);
        System.out.println(Objects.isNull(methodA));
        System.out.println(Objects.isNull(methodB));

        Method[] methods = B.class.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
