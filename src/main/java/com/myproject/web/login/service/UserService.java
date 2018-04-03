package com.myproject.web.login.service;

import com.myproject.datasource.domain.SaUserInfo;

public interface UserService {
	
	/**
	 * @param saUserInfo
	 * @return
	 */
	public SaUserInfo login(SaUserInfo saUserInfo);
	
	public SaUserInfo loginCache(String account, String password);
}
