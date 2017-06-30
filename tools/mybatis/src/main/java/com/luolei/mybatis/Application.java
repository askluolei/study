package com.luolei.mybatis;

import com.luolei.mybatis.dao.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/6/9
 */
public class Application {

    public static void main(String[] args) throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        System.out.println(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource().getConnection().getMetaData().getDatabaseProductName());
        String databaseId = sqlSessionFactory.getConfiguration().getDatabaseId();
        System.out.println(databaseId);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        int result = blogMapper.select();
        System.out.println("result：" + result);
        sqlSession.close();
    }
}
