package com.youkill.composeown.entity;

import lombok.Data;

import java.util.Date;
@Data
public class LeadingFront {
//    负责收集前端比较多的参数，搜索和页码一部分
    //这一部分是可以控制前面很多东西
    private int flag;
    private String num;
    private String looknum;
    private String classname;
    private Date gotime;
    private Date totime;
    private int limit;
}
