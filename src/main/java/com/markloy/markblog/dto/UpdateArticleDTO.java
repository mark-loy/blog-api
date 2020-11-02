package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateArticleDTO {
    @NotNull(message = "分类id不能为空")
    private Integer id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotBlank(message = "内容不能为空")
    private String context;

    @NotNull(message = "分类不能为空")
    private Integer categoryId;

    @NotBlank(message = "标签不能为空")
    private String tags;

    private String showImg;

}
