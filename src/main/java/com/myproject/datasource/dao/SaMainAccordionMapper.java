package com.myproject.datasource.dao;

import com.myproject.datasource.domain.SaMainAccordion;

public interface SaMainAccordionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaMainAccordion record);

    int insertSelective(SaMainAccordion record);

    SaMainAccordion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaMainAccordion record);

    int updateByPrimaryKey(SaMainAccordion record);
}