package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class Article {

    private Long id;
    private String title;
    private String context;
    private Long userId;
    private Long categoryId;
    private String tagsId;
    private Integer viewCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;

}
