package com.luolei.cxfserver.start;

import org.apache.cxf.annotations.Logging;
import org.apache.cxf.annotations.WSDLDocumentation;

import javax.jws.WebService;

/**
 * Describe :
 * Author : 罗雷
 * Date : 2017/7/4
 */
@WebService
@Logging(inLocation = "<stdout>", outLocation = "<stdout>")
public interface UserService {

    /**
     * webservice 需要参数，不然会有错误信息
     * @param name
     * @return
     */
    @WSDLDocumentation("查询用户")
    User getUser(String name);
}
