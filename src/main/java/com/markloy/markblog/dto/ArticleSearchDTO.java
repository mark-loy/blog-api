package com.markloy.markblog.dto;

import lombok.Data;

@Data
public class ArticleSearchDTO {

    /**
     * 起始查询下标
     */
    private Integer count;

    /**
     * 当页显示数
     */
    private Integer size;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * title查询
     */
    private String search;

}
