package com.luolei.p6spy;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 罗雷 on 2017/6/7.
 */
public class H2Tests {

    @Test
    public void testConnect() throws Exception {
        Class.forName("org.h2.Driver");
        //url 里面就指定的路径就是数据库文件存放的位置
        //根据url，来区分数据库允许模式
        /*
            jdbc:h2:~/test    内置运行模式  最后面为数据库存储文件路径
            jdbc:h2:mem:     私有内存模式
            jdbc:h2:mem:test_mem  命名内存模式
            jdbc:h2:tcp://dbserv:8084/~/sample  服务模式，可以远程连接
            jdbc:h2:ssl://localhost:8085/~/sample;  服务模式，可以用TSL远程连接


            jdbc:h2:file:~/sample;USER=sa;PASSWORD=123  指定用户名密码
            jdbc:h2:file:~/sample;INIT=RUNSCRIPT FROM '~/create.sql'\;RUNSCRIPT FROM '~/populate.sql'  启动的时候执行脚本
            jdbc:h2:<url>;DB_CLOSE_ON_EXIT=FALSE   JVM停止 不停数据库
            jdbc:h2:file:~/secure;CIPHER=AES   加密文件
         */
        Connection connection = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
        String sql = "create table IF not EXISTS myuser" +
                "( id int ," +
                "name varchar(32)," +
                "constraint pk_mysuer PRIMARY KEY (id)"
                + ")";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

        sql = "INSERT INTO myuser(id, name) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "luolei");
        preparedStatement.execute();

        sql = "SELECT * from myuser";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(String.format("id:%s,name:%s", id, name));
        }
        connection.close();
    }
}
