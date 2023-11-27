package com.youkill.composeown.controller;


import com.youkill.composeown.dao.CommentsDAO;
import com.youkill.composeown.entity.Comments;
import com.youkill.composeown.service.CommentService;
import com.youkill.composeown.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Comments")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/list")
    public List<Comments> list(@RequestBody Map<String, String> Data) {
        String ids = Data.get("title");
        int first = Integer.parseInt(Data.get("page"));
        int number = Integer.parseInt(Data.get("num"));
        return commentService.list(ids, first, number);
    }
    /**
    * @Author: youkill
    * @Description: 对于这个地方的方法我需要留言的方式，对传递过来的title或者是txt进行检验
    * @DateTime: 2023/11/9 16:50
    */
    @PostMapping("/insert")
    public boolean insert(@RequestBody Map<String, String> Data) {
        String title = Data.get("title");
        String txt=Data.get("txt");
        int flag = 0;
        String ids = "";
        if (title != null) {
            flag = Integer.parseInt(Data.get("flag"));
            ids = Data.get("ids");
        }
        Comments comments = new Comments();
        comments.setFileid(title);
        comments.setUsername("用户");
        comments.setTxt(txt);
        if (flag == 1) {
            return commentService.insertCommentRevertid(comments, ids);
        } else {
            return commentService.insertComment(comments);
        }
    }
}
