package com.youkill.composeown.service;

import com.youkill.composeown.dao.ArticlesDAO;
import com.youkill.composeown.dao.InitsDAO;
import com.youkill.composeown.entity.Articles;
import com.youkill.composeown.entity.Picture;
import com.youkill.composeown.utils.ImgCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {
    /**
    * @Author: youkill
    * @Description: 对前面图片的显示展示
    * @DateTime: 2023/11/6 15:46
    */
    @Autowired
    ArticlesDAO articlesDAO;
    @Autowired
    InitsDAO initsDAO;
    /**
    * @Author: youkill
    * @Description: create table Show pictures
    * @DateTime: 2023/11/7 11:14
    */
    public List<Picture> ShowIndexPicture(){
        List<Picture> pictures=new ArrayList<>();
        String path=initsDAO.SelectSystemValue("SystemTmp").getValue();
        List<Articles> articles=articlesDAO.SelectArticlesLimit(3);
        for (int i = 0; i <articles.size() ; i++) {
            ImgCreate.TextAndImg(path+"\\"+articles.get(i).getTitle()+".png","前"+i+"名最新文章:"+articles.get(i).getTitle());
            Picture picture=new Picture();
            picture.setImgId(articles.get(i).getTitle());
            picture.setImgPath("http://127.0.0.1:9191/img/tmp/"+articles.get(i).getTitle()+".png");
            pictures.add(i,picture);
        }
        return pictures;
    }

}
