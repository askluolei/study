package com.luolei.guice.ch9;

import java.time.LocalDate;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    @NotOnWeekends
    public void hello(LocalDate date) {
        System.out.println("Hello work day " + date.toString());
    }
}
