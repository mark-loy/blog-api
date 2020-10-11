package com.markloy.markblog.service.impl;

import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


}
