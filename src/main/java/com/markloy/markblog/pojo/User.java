package com.markloy.markblog.pojo;

import lombok.Data;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String pet_name;
    private String avatar;
    private String email;
    private int articleCount;
    private int state;
    private long gmtCreate;
    private long gmtModified;

}