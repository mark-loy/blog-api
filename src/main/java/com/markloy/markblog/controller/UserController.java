package com.markloy.markblog.controller;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.pojo.User;
import com.markloy.markblog.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录接口
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public ResultDTO login(@Validated @RequestBody LoginDTO loginDTO) {
        User user = userMapper.findByUsername(loginDTO.getUsername());
        if (user == null)
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        if (!user.getPassword().equals(loginDTO.getPassword()))
            throw new CustomizeException(CustomizeErrorCode.PASSWORD_ERROR);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        HashMap<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        String token = JwtUtil.createToken(map);
        userDTO.setToken(token);
        return ResultDTO.success(userDTO);
    }


}
