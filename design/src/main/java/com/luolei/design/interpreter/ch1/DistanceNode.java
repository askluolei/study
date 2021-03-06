package com.luolei.design.interpreter.ch1;

/**
 * 距离解释：终结符表达式
 *
 * @author luolei
 * @date 2017-03-30 16:14
 */
public class DistanceNode extends AbstractNode {
    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    //距离表达式的解释操作
    public String interpret() {
        return this.distance;
    }
}
