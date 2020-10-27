package com.markloy.markblog.controller;

import com.markloy.markblog.dto.GithubUserDTO;
import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.service.UserService;
import com.markloy.markblog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 后台管理系统登录api
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public ResultDTO backLogin(@Validated @RequestBody LoginDTO loginDTO) {
        //检查用户，获取用户信息
        UserDTO userDTO = userService.userLogin(loginDTO);
        //生成token
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userDTO.getUsername());
        String token = JwtUtil.createToken(map);
        //用户登录信息的统一封装
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", userDTO);
        hashMap.put("token", token);
        return ResultDTO.success(hashMap);
    }

    /**
     * 保存GitHub登录的用户信息api
     * @param githubUserDTO
     * @return
     */
    @PostMapping("save_github_user")
    public ResultDTO saveGithubUser(@Validated @RequestBody GithubUserDTO githubUserDTO) {
        Map<String, Object> map =  userService.saveGithubUser(githubUserDTO);
        return ResultDTO.success(map);
    }

}
