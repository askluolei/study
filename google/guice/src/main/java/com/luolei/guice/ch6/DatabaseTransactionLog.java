package com.luolei.guice.ch6;

/**
 * Created by 罗雷 on 2017/4/12.
 */
public class DatabaseTransactionLog {

    private DatabaseConnection connection;

    public DatabaseTransactionLog(DatabaseConnection connection) {
        this.connection = connection;
//        throw new RuntimeException("test");
    }

    public void hello() {
        System.out.println("hello");
        connection.world();
    }
}
