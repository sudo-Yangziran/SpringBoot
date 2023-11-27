package com.youkill.composeown.service;

import com.youkill.composeown.dao.CommentsDAO;
import com.youkill.composeown.entity.Comments;
import com.youkill.composeown.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    CommentsDAO commentsDAO;
    public List<Comments> list(String ids,int index,int num){
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("ids", ids);
        parameterMap.put("first",10);
        parameterMap.put("last", 1);
        return  commentsDAO.SelectList(parameterMap);
    }
    /**
    * @Author: youkill
    * @Description: 关于评论区的一个获取,下列是创建评论区的表
     *  需要写两个，一个是进行新的插入，另外一个需要回复的接口
    * @DateTime: 2023/11/8 15:46
    */
    public boolean insertComment(Comments comments){
        comments.setLikenumber(0);
        if (comments==null){
            return false;
        }
        comments.setIds(String.valueOf(new SnowFlake(1,1,1).nextId()));
        comments.setCreateTime(new Date());
        //这里可以做一个进行判断
        return commentsDAO.AddComments(comments);
    }
    public boolean insertCommentRevertid(Comments comments,String revertid){
        if (comments==null){
            return false;
        }
        comments.setIds(String.valueOf(new SnowFlake(1,1,1).nextId()));
        comments.setCreateTime(new Date());
        comments.setRevertid(revertid);


        return commentsDAO.AddComments(comments);
    }

}
