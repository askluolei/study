package com.luolei.jdk.utils;

/**
 * 前置条件判断工具类
 * @author luolei
 * @Date 2015年8月12日
 */
public final class PreConditions {
	
	/**
	 * 检查对象是否为空
	 * @param reference	待检查的对象
	 * @return	返回原对象，为空抛出NullPointerException
	 */
	public static <T> T checkNotNull(T reference) {
		if(reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}
	
	/**
	 * 检查对象是否为空
	 * @param reference	待检查的对象
	 * @param msg	异常描述
	 * @return	返回原对象，为空抛出NullPointerException
	 */
	public static <T> T checkNotNull(T reference, String msg) {
		if(reference == null) {
			throw new NullPointerException(msg);
		}
		return reference;
	}
	
	/**
	 * 检查表达式是否为真	为假，则抛出IllegalArgumentException
	 * @param expression	表达式
	 */
	public static void checkArgument(boolean expression) {
		if(!expression) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 检查表达式是否为真	为假，则抛出IllegalArgumentException
	 * @param expression	表达式
	 * @param errorMessage	错误描述
	 */
	public static void checkArgument(boolean expression, String errorMessage) {
		if(!expression) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	/**
	 * 检查是否是一个有效的position，是 则返回该位置，否则抛出IndexOutOfBoundsException
	 * @param index	position
	 * @param size	数组大小
	 * @param desc	错误信息
	 * @return	index
	 */
	public static int checkPositionIndex(int index, int size, String desc) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
		}
		return index;
	}
	
	/**
	 * 检查是否是一个有效的position，是 则返回该位置，否则抛出IndexOutOfBoundsException
	 * @param index	position
	 * @param size	数组大小
	 * @return	index
	 */
	public static int checkPositionIndex(int index, int size) {
		return checkPositionIndex(index, size, "index");
	}
	
	/**
	 * 抛出异常时显示的错误信息
	 * @param index
	 * @param size
	 * @param desc
	 * @return
	 */
	private static String badPositionIndex(int index, int size, String desc) {
		if(index < 0) {
			return format("%s (%s) 不能为负", desc, index);
		}
		else if(size < 0) {
			throw new IllegalArgumentException("size 不能为负 :"+size);
		}
		else {  //index > size
			return format("%s (%s) 不能比 size (%s) 大", desc, index, size);
		}
	}
	
	
	/**
	 * 
	 * @param template
	 * @param args
	 * @return
	 */
	private static String format(String template, Object...args) {
		template = String.valueOf(template);
		StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
		int templateStart = 0;
	    int i = 0;
	    while (i < args.length) {
	      int placeholderStart = template.indexOf("%s", templateStart);
	      if (placeholderStart == -1) {
	        break;
	      }
	      builder.append(template.substring(templateStart, placeholderStart));
	      builder.append(args[i++]);
	      templateStart = placeholderStart + 2;
	    }
	    builder.append(template.substring(templateStart));

	    if (i < args.length) {
	      builder.append(" [");
	      builder.append(args[i++]);
	      while (i < args.length) {
	        builder.append(", ");
	        builder.append(args[i++]);
	      }
	      builder.append(']');
	    }

	    return builder.toString();
	  }
	
}
