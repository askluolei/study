package com.luolei.quartz.listener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author luolei
 * @date 2017-03-27 14:37
 */
public class HelloJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello Quartz!");
        //Throw exception for testing
        throw new JobExecutionException("Testing Exception");
    }
}
