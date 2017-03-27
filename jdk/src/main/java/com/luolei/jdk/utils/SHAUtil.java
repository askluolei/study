package com.luolei.jdk.utils;

import java.security.MessageDigest;

/**
 * SHA加密算法，不能解密
 * @author luolei
 * @Date 2015年12月25日
 * @Time 下午9:50:00
 */
public final class SHAUtil {
	
	private static final char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static final String SHA(String data){
		try{
			byte[] btInput = data.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("SHA");
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
	 * 
	 * @param bytes
	 * @return
	 */
	public static final String SHA(byte[] bytes) {
		try{
			MessageDigest mdInst = MessageDigest.getInstance("SHA");
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
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
