package com.youkill.composeown.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comments
 * @author 
 */
@Data
public class Comments implements Serializable {
    private String ids;

    private String fileid;

    private String txt;

    private Date createTime;

    private String username;

    private String revertid;

    private Integer likenumber;

    private static final long serialVersionUID = 1L;
}