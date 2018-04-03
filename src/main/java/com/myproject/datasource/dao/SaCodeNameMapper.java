package com.myproject.datasource.dao;

import org.springframework.stereotype.Repository;

import com.myproject.datasource.domain.SaCodeName;

@Repository
public interface SaCodeNameMapper {
    int insert(SaCodeName record);

    int insertSelective(SaCodeName record);
}