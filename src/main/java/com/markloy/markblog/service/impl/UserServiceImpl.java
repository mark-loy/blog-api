package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.pojo.User;
import com.markloy.markblog.service.UserService;
import com.markloy.markblog.util.JwtUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {

        User user = userMapper.findByUsername(loginDTO.getUsername());
        //判断用户是否存在
        if (user == null)
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        //对密码进行md5加密
        String passwordEncode = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        //判断密码是否正确
        if (!user.getPassword().equals(passwordEncode))
            throw new CustomizeException(CustomizeErrorCode.PASSWORD_ERROR);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
