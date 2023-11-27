package com.youkill.composeown.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * files
 * @author 
 */
@Data
public class Files implements Serializable {
    private String ids;

    private String realname;

    private static final long serialVersionUID = 1L;

    public Files(String ids, String realname) {
        this.ids = ids;
        this.realname = realname;
    }
}