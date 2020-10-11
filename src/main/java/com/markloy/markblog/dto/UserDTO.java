package com.markloy.markblog.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String username;
    private String avatar;
    private String email;
    private String petName;
    private int articleCount;
    private int state;
    private String token;
}
