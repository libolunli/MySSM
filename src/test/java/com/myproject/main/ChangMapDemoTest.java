package com.myproject.main;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.myproject.po.TestPo;
import com.myproject.pub.util.BeanUtil;

public class ChangMapDemoTest {
	private Map<String ,Object> getMapInfo(){
		Map<String ,Object> map = new HashMap<String ,Object>(); 
		map.put("name", "TESTNAME");
		map.put("vauleType", "TYPE1");
		return map;
	}
	
	public static void main(String[] args) {
		ChangMapDemoTest changMap = new ChangMapDemoTest();
		try {
			TestPo testPo = BeanUtil.convertMap(TestPo.class, changMap.getMapInfo());
			System.out.println("Test");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
