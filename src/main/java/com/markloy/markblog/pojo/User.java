package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String petName;
    private String avatar;
    private String email;
    private Integer articleCount;
    private Integer state;
    private Long gmtCreate;
    private Long gmtModified;

}