package com.myproject.pub.util;

import java.util.List;

public class ListUtil {
	
	public static boolean isEmpty(List list){
		if(list == null || list.size() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(List list){
		if(list == null || list.size() == 0){
			return false;
		}
		return true;
	}
}
