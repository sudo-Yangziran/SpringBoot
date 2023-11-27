package com.youkill.composeown.dao;

import com.youkill.composeown.entity.Files;

public interface FilesDAO {
    int deleteByPrimaryKey(String ids);

    int insert(Files record);

    int insertFiles(Files record);

    Files selectByPrimaryKey(String ids);

    int updateByPrimaryKeySelective(Files record);

    int updateBySao(Files record);
}