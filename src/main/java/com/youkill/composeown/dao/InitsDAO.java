package com.youkill.composeown.dao;

import com.youkill.composeown.entity.Inits;

public interface InitsDAO {
    int deleteByPrimaryKey(String systemvalue);

    int insert(Inits record);

    int insertSelective(Inits record);

    Inits SelectSystemValue(String systemvalue);

    int updateByPrimaryKeySelective(Inits record);

    int updateByPrimaryKey(Inits record);
}