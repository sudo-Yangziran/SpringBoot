package com.youkill.composeown.controller;


import com.youkill.composeown.entity.Articles;
import com.youkill.composeown.entity.Picture;
import com.youkill.composeown.service.ArticleService;
import com.youkill.composeown.service.CommonService;
import com.youkill.composeown.service.CommonUserService;
import com.youkill.composeown.utils.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/index")
@RequiredArgsConstructor
public class IndexController {
    @Autowired
    CommonService commonService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("success");
    }
    @GetMapping("/show")
    public List<Picture> ShowPicture(){
        return commonService.ShowIndexPicture();
    }
    @PostMapping("/article")
    public Articles GetArticle(@RequestBody Map<String,String> Data){
        String name=Data.get("articlename");
        if (name!=null){
           return articleService.GetArticle(name);
        }else {
            return null;
        }
    }
}
