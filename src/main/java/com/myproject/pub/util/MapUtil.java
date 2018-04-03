package com.myproject.pub.util;

import java.util.Map;

public class MapUtil {
	public static boolean isNotEmpty(Map map){
		if(map==null || map.size()<1){
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty(Map map){
		if(map==null || map.size()<1){
			return true;
		}
		return false;
	}
}
