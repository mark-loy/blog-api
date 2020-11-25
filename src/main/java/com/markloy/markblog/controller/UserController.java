package com.markloy.markblog.controller;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.dto.VisitorLoginDTO;
import com.markloy.markblog.service.UserService;
import com.markloy.markblog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/private/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存GitHub登录的用户信息api
     * @param visitorLoginDTO
     * @return
     */
    @PostMapping("save/user/github")
    public ResultDTO saveGithubUser(@Validated @RequestBody VisitorLoginDTO visitorLoginDTO) {
        Map<String, Object> map =  userService.saveVisitorUser(visitorLoginDTO);
        return ResultDTO.success(map);
    }

    /**
     * 保存QQ登录的用户信息api
     * @param visitorLoginDTO
     * @return
     */
    @PostMapping("save/user/qq")
    public ResultDTO saveQQUser(@Validated @RequestBody VisitorLoginDTO visitorLoginDTO) {
        Map<String, Object> map =  userService.saveVisitorUser(visitorLoginDTO);
        return ResultDTO.success(map);
    }

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
}
