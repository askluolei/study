package com.luolei.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author luolei
 * @date 2017-03-27 14:20
 */
public class log4jExample {

    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.debug("hello");
        logger.info("hello");
        logger.warn("hello");
    }
}
