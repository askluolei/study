package com.luolei.design.mediator.ch1;

/**
 * 文本框类：具体同事类
 *
 * @author luolei
 * @date 2017-03-30 19:18
 */
public class TextBox extends Component {
    public void update() {
        System.out.println("客户信息增加成功后文本框清空。");
    }

    public void setText() {
        System.out.println("文本框显示：小龙女。");
    }
}