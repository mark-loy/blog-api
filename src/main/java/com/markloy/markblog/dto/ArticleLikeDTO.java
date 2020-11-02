package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleLikeDTO {

    @NotNull(message = "访客不能为空")
    private Integer visitorId;

    @NotNull(message = "文章不能为空")
    private Integer articleId;

    /**
     * 点赞类型
     * 1. 点赞
     * 0. 取消点赞
     */
    @NotNull(message = "类型不能为空")
    private Integer type;
}
