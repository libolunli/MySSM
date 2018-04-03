package com.myproject.datasource.dao;

import org.springframework.stereotype.Repository;

import com.myproject.datasource.domain.SaUserInfo;

@Repository
public interface SaUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaUserInfo record);

    int insertSelective(SaUserInfo record);

    SaUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaUserInfo record);

    int updateByPrimaryKey(SaUserInfo record);
    
    /**
     * 查询是否存在有效用户
     * @param record
     * @return
     */
    SaUserInfo login(SaUserInfo record);
}