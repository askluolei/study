package com.luolei.quartz.multijob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author luolei
 * @date 2017-03-27 14:52
 */
public class JobA implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("Job A is runing //every 5 seconds ");
    }

}
