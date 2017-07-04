package com.luolei.cxfserver.start;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.annotations.WSDLDocumentation;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Describe :
 * 可以通过一系列注解来开启扩展功能
 * @Logging 开启日志
 * @GZIP 开启gzip功能 这个如果客户端不支持也没用
 * @WSDLDocumentation 在wsdl文档中展示
 *
 * Author : 罗雷
 * Date : 2017/7/4
 */
@WebService
@Logging
@GZIP(threshold = 1024 * 1024)
public interface HelloWorld {

    @WSDLDocumentation("Hello World!")
    String sayHi(@WebParam(name = "text") String text);
}
