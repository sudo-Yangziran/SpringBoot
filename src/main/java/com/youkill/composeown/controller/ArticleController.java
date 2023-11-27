package com.youkill.composeown.controller;


import com.youkill.composeown.dao.BlogUserDao;
import com.youkill.composeown.entity.Articles;
import com.youkill.composeown.entity.Files;
import com.youkill.composeown.entity.LeadingFront;
import com.youkill.composeown.service.ArticleService;
import com.youkill.composeown.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Article")
@RequiredArgsConstructor
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private BlogUserDao blogUserDao;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/count")
    public int count(){
        return articleService.count();
    }
    @PostMapping("/list")
    public List<Articles> list(@RequestBody Map<String,Integer> map){
        return articleService.list(map);
    }
    @PostMapping("/upload")
    public String Upload(@RequestParam("file")MultipartFile multipartFile){
        if (multipartFile==null){
            return "";
        }
        articleService.Upload(multipartFile);
        return "success";
    }
    @PostMapping("/uploadImg")
    public String UploadImg(@RequestParam("file")MultipartFile multipartFile){
        if (multipartFile==null) {
            return "";
        }
            return articleService.UploadPhotos(multipartFile);
    }
    @PostMapping("/newfile")
    public boolean newArticle(@RequestBody Map<String,String> Data, HttpServletRequest httpServletRequest){
        String username=httpServletRequest.getHeader("Authorization");
        if (username==null){
            return false;
        }else {
           username=jwtService.extractUsername(username);
//           id=blogUserDao.selectByPrimaryKey(username);
        }
        if (articleService.new_articles(Data.get("filename"),username)){
            return true;
        }
        return false;
    }
    /**
    * @Author: youkill
    * @Description: 对文本进行保存,待解决--->添加一块检验是否是这个用户
    * @DateTime: 2023/11/6 10:48
    */
    @PostMapping("/writefile")
    public boolean saveArticle(@RequestBody Map<String,String> Data){
        String filename=Data.get("filename");
        String text=Data.get("content");
        System.out.println("文件名:"+filename+"文件内容:"+text);
        articleService.write_article(filename,text);
        return true;
    }
    /**
    * @Author: youkill
    * @Description: 这边迭代的时候有问题，骚操作勿模仿
    * @DateTime: 2023/10/24 17:46
    */
    @PostMapping("/renfile")
    public boolean reArticle(@RequestParam("form")Map<String,String> map){
        if (articleService.re_articles(new Files(map.get("oldname"),map.get("newname")))){
            return true;
        }
        return false;
    }
    /**
    * @Author: youkill
    * @Description: 制定一下规则吧:按照参数来制定，默认主页是按照flag(顺序还是倒序),looknum(浏览数量),word(搜索关键字),class(分类),gotime-totime(时间区域)
    * @DateTime: 2023/10/31 15:53
    */
    @PostMapping("/indexlist")
    public List<Articles> Index_list(@RequestBody LeadingFront require){


        return articleService.indexArticleList(require);
    }
    @PostMapping("/article")
    public Articles GetArticle(@RequestBody Map<String,String> map){
        String title=map.get("title");
        if (!title.isEmpty()){
           return articleService.GetArticle(title);
        }
        return null;
    }
    @PostMapping("/state")
    public int notArticleState(@RequestBody  Map<String,String> map){
        String title=map.get("title");
        return articleService.notState(title);
    }
}
