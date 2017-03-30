package com.luolei.design.bridge.ch2;

/**
 * Linux操作系统实现类：具体实现类
 *
 * @author luolei
 * @date 2017-03-30 10:42
 */
public class LinuxImp implements ImageImp {
    public void doPaint(Matrix m) {
        //调用Linux系统的绘制函数绘制像素矩阵
        System.out.print("在Linux操作系统中显示图像：");
    }
}