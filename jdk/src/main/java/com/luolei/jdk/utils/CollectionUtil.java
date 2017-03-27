package com.luolei.jdk.utils;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtil {

	/**
	 * 判断 collection 是否为空 
	 * @param collection
	 * @return	是否为空 
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}
	
	/**
	 * 判断 collection 是否为非空 
	 * @param collection
	 * @return	是否为非空 
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 * 判断 map 是否为空 
	 * @param map
	 * @return	是否为空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}
	
	/**
	 * 判断 map 是否为非空 
	 * @param map
	 * @return	是否为非空 
	 */
	public static boolean isNotEmpty(Map<? ,?> map) {
		return !isEmpty(map);
	}
}
