package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class Article {

    private Integer id;
    private String title;
    private String description;
    private String context;
    private Integer userId;
    private Integer categoryId;
    private String tagsId;
    private Integer viewCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;

}
