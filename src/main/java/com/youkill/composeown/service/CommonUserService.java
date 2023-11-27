package com.youkill.composeown.service;

import com.youkill.composeown.dao.BlogUserDao;
import com.youkill.composeown.entity.BlogUser;
import com.youkill.composeown.utils.DateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonUserService {
    @Autowired
    BlogUserDao blogUser;
    /**
    * @Author: youkill
    * @Description: 这是对用户列表进行操作的
    * @DateTime: 2023/10/6 17:25
    *20223/10/7对应的几个接口完成.
     */
    public List<BlogUser> ListUser(){
        List<BlogUser> blogUsers=blogUser.ListUser();
        if (blogUsers!=null){

        }
        return blogUsers;
    }
    public ResponseEntity AddUser(BlogUser blogUser){
        int status=500;

        return null;
    }
}
