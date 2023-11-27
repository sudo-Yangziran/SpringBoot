package com.youkill.composeown.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PageArticles {
    private String title;
    private String createowner_id;
    private Date create_time;
    private Date last_save_time;
    private int satus;
}
