package com.markloy.markblog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateTagDTO {

    @NotNull(message = "标签id不能为空")
    private Integer id;

    @NotBlank(message = "标签名不能为空")
    @Length(max = 16)
    private String tagName;
}
