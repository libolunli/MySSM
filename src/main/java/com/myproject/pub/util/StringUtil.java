package com.myproject.pub.util;

public class StringUtil {
	
	public static boolean isEmpty(String key){
		if(key == null || key == ""){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String key){
		if(key == null || key == ""){
			return false;
		}
		return true;
	}
}
