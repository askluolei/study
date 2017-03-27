package com.luolei.jdk.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 流操作工具类
 * Created with IntelliJ IDEA.
 * User: luolei
 * Date: 2015/12/17
 * Time: 22:29
 */
public final class StreamUtil {

    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 将 输入流 复制到 输出流
     * @param inputStream
     * @param outputStream
     */
    public static void copyString(InputStream inputStream, OutputStream outputStream) {
        try {
            int length;
            byte[] buffer = new byte[4 * 1024];
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
}
