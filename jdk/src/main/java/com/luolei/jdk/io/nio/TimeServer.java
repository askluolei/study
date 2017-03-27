package com.luolei.jdk.io.nio;

import java.io.IOException;

/**
 * @author luolei
 * @date 2017-03-17 15:13
 */
public class TimeServer {
    public static void main(String[] args) throws IOException{
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //默认
            }
        }
        MultiplexerTimeServer timeServer =new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
