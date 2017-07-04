package com.luolei.reactor.why;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author : LuoLei
 * @Desc :
 * @Date : created at 2017/5/22 22:44
 * @Modified by:
 */
public class ch1 {

    private ExecutorService threadPool = Executors.newFixedThreadPool(8);
    final List<String> batchs = new ArrayList<>();

    Callable<String> t = new Callable<String>() {
        @Override
        public String call() throws Exception {
            String msg = "Hello";
            synchronized (batchs) {
                String result = callDatabase(msg);
                batchs.add(result);
                return result;
            }
        }
    };

    public static String callDatabase(String msg) {
        return msg;
    }

    public static void main(String[] args) {

    }
}
