package com.luolei.mybatis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/9
 */
@Data
public class Blog implements Serializable {

    private Long id;
    private String title;
    private String content;
}
