package com.markloy.markblog.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String avatar;
    private String email;
    private String petName;
    private Integer articleCount;
    private Integer state;
}
