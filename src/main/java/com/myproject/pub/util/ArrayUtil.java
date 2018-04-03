package com.myproject.pub.util;

public class ArrayUtil {
	public static String getStringFromArray(Object[] items,String separator){
		if(items != null && items.length > 0){
			if(items.length == 1){
				return String.valueOf(items[0]);
			}else {
				StringBuilder builder = new StringBuilder();
				for(Object item : items){
					builder.append(separator).append(item.toString());
				}
				return builder.substring(separator.length());
			}
		}
		else {
			return null;
		}
	}
}
