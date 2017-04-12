package com.luolei.guice.ch3;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class JdbcProperties {

    @Inject
    @Named("jdbc.url")
    private String url;

    @Inject
    @Named("jdbc.username")
    private String username;

    @Inject
    @Named("jdbc.password")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
