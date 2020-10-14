package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class Tag {

    private Integer id;
    private String tagName;
    private Integer articleCount;
    private Long gmtCreate;
    private Long gmtModified;

}
