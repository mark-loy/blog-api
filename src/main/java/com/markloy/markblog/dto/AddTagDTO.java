package com.markloy.markblog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddTagDTO {

    @NotBlank(message = "标签名不能为空")
    @Length(max = 16)
    private String tagName;

}
