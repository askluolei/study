package com.luolei.jdk.utils;

public final class ArrayUtil {

	/**
	 * 判断数组是否为空
	 * @param array	数组
	 * @return	是否为空
	 */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
	
	/**
	 * 判断数组是否非空
	 * @param array	数组
	 * @return	是否非空
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}
}
