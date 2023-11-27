package com.youkill.composeown.controller;


import com.alibaba.fastjson.JSON;
import com.youkill.composeown.entity.BlogUser;
import com.youkill.composeown.service.CommonUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class CommonUserController {
    @Autowired
    CommonUserService commonUserService;
    @GetMapping("/users")
    public ResponseEntity<String> ListUser(){
        List<BlogUser> blogUsers=commonUserService.ListUser();
        if(blogUsers==null){
            return ResponseEntity.ok("not found user");
        }

        return ResponseEntity.ok(JSON.toJSONString(blogUsers));
    }
    @PostMapping("/addUser")
    public ResponseEntity<String> AddUser(@RequestBody BlogUser blogUser){
        /**
        * @Author: youkill
        * @Description: 先搁置最后添加
        * @DateTime: 2023/10/18 15:57
        */
        return null;
    }
}
