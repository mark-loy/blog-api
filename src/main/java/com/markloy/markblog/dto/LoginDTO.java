package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "用户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
