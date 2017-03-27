package com.luolei.jdk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5摘要算法
 * @author luolei
 * @Date 2015年12月25日
 * @Time 下午9:42:35
 */
public final class MD5Util {
	
	private static final char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	
	/**
	 * 输入的字符串返回摘要后的16进制字符串
	 * @param s	输入字符串
	 * @return	摘要后的16进制字符串
	 */
    public final static String MD5(String s) {
              
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 输入字节数组，返回摘要
     * @param bytes	字节数组
     * @return	摘要
     */
    public final static String MD5(byte[] bytes) {
    	MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        // 使用指定的字节更新摘要
        mdInst.update(bytes);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
    
}
