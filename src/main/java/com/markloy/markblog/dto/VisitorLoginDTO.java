package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VisitorLoginDTO {

    @NotNull(message = "账户id不能为空")
    private String accountId;

    @NotBlank(message = "访客名不能为空")
    private String visitorName;

    @NotBlank(message = "头像不能为空")
    private String avatarUrl;
}
