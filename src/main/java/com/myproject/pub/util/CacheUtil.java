package com.myproject.pub.util;

import java.util.List;

import com.myproject.datasource.domain.SaCodeName;
import com.myproject.datasource.domain.SaMainAccordion;
import com.myproject.datasource.domain.SaUserInfo;
import com.myproject.pub.cache.po.CacheConfigManager;

public class CacheUtil {
	@SuppressWarnings("unchecked")
	public static List<SaCodeName> getSaCodeName(String codeType){
		List<SaCodeName> saCodeNameList = CacheConfigManager.getByIndex("SA_CODE_NAME", "CODETYPE", List.class, codeType);
		return saCodeNameList;
	}
	
	public static SaUserInfo getUserInfo(String account,String password){
		SaUserInfo saUserInfo = CacheConfigManager.getByIndex("SA_USER_INFO", "ACCOUNT,PASSWORD", SaUserInfo.class, account,password);
		return saUserInfo;
	}
	
	@SuppressWarnings("unchecked")
	public static List<SaMainAccordion> getMainAccordion(String auth){
		List<SaMainAccordion> saMainAccordionList = CacheConfigManager.getByIndex("SA_MAIN_ACCORDION", "AUTH", List.class, auth);
		return saMainAccordionList;
	}
}
