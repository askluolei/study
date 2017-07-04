package com.luolei.cxfserver.start;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/7/4
 */
public class HelloServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Server");
        HelloWorldImpl impl = new HelloWorldImpl();
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(HelloWorld.class);
        svrFactory.setServiceBean(impl);
        svrFactory.setAddress("http://localhost:9000/services/helloWorld");
//        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
//        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        svrFactory.create();

        UserServiceImpl userService = new UserServiceImpl();
        JaxWsServerFactoryBean userFactory = new JaxWsServerFactoryBean();
        userFactory.setServiceClass(UserService.class);
        userFactory.setServiceBean(userService);
        userFactory.setAddress("http://localhost:9000/services/userService");
//        userFactory.getInInterceptors().add(new LoggingInInterceptor());
//        userFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        userFactory.create();
    }
}
