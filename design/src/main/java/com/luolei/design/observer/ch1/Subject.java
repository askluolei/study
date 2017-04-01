package com.luolei.design.observer.ch1;

import java.util.ArrayList;

/**
 * @author luolei
 * @date 2017-03-31 11:17
 */
public abstract class Subject {
    //定义一个观察者集合用于存储所有观察者对象
    protected ArrayList<Observer> observers = new ArrayList<>();

    //注册方法，用于向观察者集合中增加一个观察者
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //注销方法，用于在观察者集合中删除一个观察者
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    //声明抽象通知方法
    public abstract void notifyObserver();
}
