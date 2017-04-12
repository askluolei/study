package com.luolei.guice.ch9;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class WeekendBlocker implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] args = methodInvocation.getArguments();
        Object obj = args[0];
        LocalDate date = (LocalDate) obj;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            System.out.println("非工作日，不提供服务");
            throw new RuntimeException(methodInvocation.getMethod().getName() + " not allowed on weekends!");
        }
        return methodInvocation.proceed();
    }
}
