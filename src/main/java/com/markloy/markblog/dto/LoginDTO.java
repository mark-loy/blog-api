package com.markloy.markblog.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "用户不能为空")
    @Length(min = 5, max = 16)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 11)
    private String password;

}
