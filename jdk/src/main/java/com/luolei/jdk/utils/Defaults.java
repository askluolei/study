package com.luolei.jdk.utils;

import static com.luolei.jdk.utils.PreConditions.checkNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 8种基本类型的默认值
 * @author luolei
 * @Date 2015年8月12日
 */
public final class Defaults {
	private Defaults() {}
	
	private static final Map<Class<?>, Object> DEFAULTS;
	
	static {
		Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
		put(map, boolean.class, false);
	    put(map, char.class, '\0');
	    put(map, byte.class, (byte) 0);
	    put(map, short.class, (short) 0);
	    put(map, int.class, 0);
	    put(map, long.class, 0L);
	    put(map, float.class, 0f);
	    put(map, double.class, 0d);
	    DEFAULTS = Collections.unmodifiableMap(map);
	}
	
	private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
		map.put(type, value);
	}
	
	/**
	 * 获得默认值
	 * @param type	类型
	 * @return	该类型的默认值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T defaultValue(Class<?> type) {
		T t = (T)DEFAULTS.get(checkNotNull(type));
		return t;
	}
	
}
