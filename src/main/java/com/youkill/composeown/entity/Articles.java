package com.youkill.composeown.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * articles
 * @author 
 */
@Data
public class Articles implements Serializable {
    private String id;

    private String title;

    private String createownerId;

    private Date createTime;

    private Date lastSaveTime;

    private Date releaseDate;

    private Integer status;

    private Integer looknum;

    private String img;
    private String context;

    private static final long serialVersionUID = 1L;
}