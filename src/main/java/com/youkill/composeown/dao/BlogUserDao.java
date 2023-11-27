package com.youkill.composeown.dao;

import com.youkill.composeown.entity.BlogUser;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface BlogUserDao {
    List<BlogUser> ListUser();


//    int register(BlogUser blogUser);
    BlogUser selectByPrimaryKey(String username);
    int deleteByPrimaryKey(String username);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    //查询用户所用
    BlogUser selectNameByUser(String email);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);
}