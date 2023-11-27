package com.youkill.composeown.dao;

import com.youkill.composeown.entity.Comments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommentsDAO {
    List<Comments> SelectList(Map<String, Object> parameterMap);

    boolean AddComments(Comments comments);
}