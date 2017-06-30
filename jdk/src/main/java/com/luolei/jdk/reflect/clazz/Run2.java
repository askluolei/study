package com.luolei.jdk.reflect.clazz;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/29
 */
public class Run2 {

    static class Default {
        private int a;
        public int getA() {
            return this.a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        Default d = new Default();
        System.out.println(d.getA());
    }
}
