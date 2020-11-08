package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateAdminDTO {

    @NotNull(message = "id不能为空")
    private Integer id;

    private String username;

    private String email;

    private String petName;

    private String avatar;

}
