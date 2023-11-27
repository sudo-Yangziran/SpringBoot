package com.youkill.composeown.dao;

import com.youkill.composeown.entity.Articles;
import com.youkill.composeown.entity.LeadingFront;

import java.util.List;

public interface ArticlesDAO {
    String GetFileName(String title);
    int CreateArticle(Articles articles);

    List<Articles> list(Integer page, Integer current);

    List<Articles> indexList(Integer page, Integer current);

    List<Articles> ArticleList(LeadingFront leadingFront);


    List<Articles> SelectArticlesLimit(Integer number);


    Articles GetArticl(String title);


    int notArticleState(String title);


    int count();
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Articles record);
//
//    int insertSelective(Articles record);
//
//    Articles selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Articles record);
//
//    int updateByPrimaryKey(Articles record);
}