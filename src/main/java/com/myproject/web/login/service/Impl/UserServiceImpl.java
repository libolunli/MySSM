package com.myproject.web.login.service.Impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.datasource.dao.SaUserInfoMapper;
import com.myproject.datasource.domain.SaUserInfo;
import com.myproject.pub.cache.po.CacheConfigManager;
import com.myproject.pub.util.BeanUtil;
import com.myproject.pub.util.CacheUtil;
import com.myproject.web.login.service.UserService;

@Service
@RequestMapping("userService")
public class UserServiceImpl implements UserService{

	private Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
    private SaUserInfoMapper saUserInfoMapper;
	
	public SaUserInfo login(SaUserInfo saUserInfo) {
		return saUserInfoMapper.login(saUserInfo);
	}
	
	public SaUserInfo loginCache(String account, String password){
		try {
			SaUserInfo saUserInfo = CacheUtil.getUserInfo(account, password);
			return saUserInfo;
		} catch (Exception e) {
			log.error("Map转化POJO报错!");
			return null;
		}
	}

}
