package com.luolei.jdk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodecUtil {
	
	private static final String MD5 = "MD5";
	
	private static final String SHA1 = "SHA1";
	
	/**
	 * 按照指定方法加密
	 * @param str
	 * @param type
	 * @return
	 */
	private static String codec(String str,String type){
		MessageDigest digest = null;
		try{
			digest = MessageDigest.getInstance(type);
			digest.reset();
			digest.update(str.getBytes());
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] byteArray = digest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
            	md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i])); 
            }
            else {
            	md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
	}
	
	/**
	 * md5 加密
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return codec(str, MD5);
	}
	
	/**
	 * sh1加密
	 * @param str
	 * @return
	 */
	public static String SHA1(String str){
		return codec(str, SHA1);
	}
	
    /**
     * 将URL编码
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }
    
    /**
     * 将URL解码
     * @param source
     * @return
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }

}

