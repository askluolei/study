package com.luolei.cxfserver.start;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/7/4
 */
public class HelloClient {

    @Test
    public void test() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setAddress("http://localhost:9000/services/helloWorld");
        HelloWorld client = factory.create(HelloWorld.class);
        System.out.println(client.sayHi(" world"));
    }

    @Test
    public void testUser() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setAddress("http://localhost:9000/services/userService");
        UserService userService = factory.create(UserService.class);
        User user = userService.getUser("123");
        System.out.println(user.getName() + " " + user.getAge());
    }

    /**
     * 测试通过wsimport 命令生成的代码
     *
     * wsimport -keep -p "com.luolei.cxfserver.start" -d "g:/test/cxf" -encoding "UTF-8" http://127.0.0.1:9000/services/helloWorld?wsdl
     * wsimport -keep -p "com.luolei.cxfserver.start" -d "g:/test/cxf" -encoding "UTF-8" http://127.0.0.1:9000/services/userService?wsdl
     */
    @Test
    public void testGenerator() {
        HelloWorldService service = new HelloWorldService();
        HelloWorld helloWorld = service.getHelloWorldPort();
        helloWorld.sayHi(" World");

        UserServiceService userServiceService = new UserServiceService();
        UserService userService = userServiceService.getUserServicePort();
        User user = userService.getUser("luolei");
        System.out.println(user.getName() + " " + user.getAge());
    }
}
