package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class Category {
    private Long id;
    private String categoryName;
    private Integer articleCount;
    private Long gmtCreate;
    private Long gmtModified;
}
