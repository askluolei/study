package com.luolei.design.simplefactory.ch3;

/**
 * @author luolei
 * @date 2017-03-29 15:45
 */
public class PieChart implements Chart {
    public PieChart() {
        System.out.println("创建饼状图！");
    }

    public void display() {
        System.out.println("显示饼状图！");
    }
}
