package com.markloy.markblog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddCategoryDTO {

    @NotBlank(message = "分类名不能为空")
    @Length(max = 16)
    private String cateName;

}
