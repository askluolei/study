package com.luolei.jdk.jmx.notification;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/9
 */
public class HelloListener implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if(handback instanceof Hello) {
            Hello hello = (Hello)handback;
            hello.printHello(notification.getMessage());
        }
    }
}
