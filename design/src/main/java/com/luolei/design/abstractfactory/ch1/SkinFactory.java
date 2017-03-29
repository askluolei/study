package com.luolei.design.abstractfactory.ch1;

/**
 * @author luolei
 * @date 2017-03-29 17:18
 */
public interface SkinFactory {
    Button createButton();

    TextField createTextField();

    ComboBox createComboBox();
}
