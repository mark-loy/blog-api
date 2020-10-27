package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.GithubUserDTO;
import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.mapper.VisitorMapper;
import com.markloy.markblog.pojo.User;
import com.markloy.markblog.pojo.UserExample;
import com.markloy.markblog.pojo.Visitor;
import com.markloy.markblog.pojo.VisitorExample;
import com.markloy.markblog.service.UserService;
import com.markloy.markblog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 验证用户名，密码
     *
     * @param loginDTO
     * @return
     */
    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(loginDTO.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        //判断用户是否存在
        if (userList == null)
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        //对密码进行md5加密
        String passwordEncode = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        //获取查询到的用户
        User user = userList.get(0);
        //判断密码是否正确
        if (!user.getPassword().equals(passwordEncode))
            throw new CustomizeException(CustomizeErrorCode.PASSWORD_ERROR);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * 保存GitHub登录的访客信息
     * @param githubUserDTO
     * @return
     */
    @Override
    public Map<String, Object> saveGithubUser(GithubUserDTO githubUserDTO) {
        // 通过accountId查询该用户是否存在
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria()
                .andAccountIdEqualTo(githubUserDTO.getAccountId());
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        // 结果集
        HashMap<String, Object> resultMap = new HashMap<>();
        // 判断当前是否为新用户
        if (visitors.size() == 1) {
            // 存在该用户，则更新该用户信息
            Visitor updateVisitor = new Visitor();
            // 设置主键
            updateVisitor.setId(visitors.get(0).getId());
            // 设置访客名
            updateVisitor.setVisitorName(githubUserDTO.getVisitorName());
            // 设置头像url
            updateVisitor.setAvatarUrl(githubUserDTO.getAvatarUrl());
            // 设置最近登录时间
            updateVisitor.setGmtModified(System.currentTimeMillis());
            // 执行update语句
            int isUpdate = visitorMapper.updateByPrimaryKeySelective(updateVisitor);
            // 判断是否修改成功
            if (isUpdate != 1) throw new CustomizeException(CustomizeErrorCode.UPDATE_USER_ERROR);
            // 在结果集中设置当前用户id
            resultMap.put("visitor_id", updateVisitor.getId());
        } else {
            // 未找到该用户，新增该用户信息
            Visitor addVisitor = new Visitor();
            // 设置账户id
            addVisitor.setAccountId(githubUserDTO.getAccountId());
            // 设置登录来源
            addVisitor.setSource(1);
            // 设置访客名
            addVisitor.setVisitorName(githubUserDTO.getVisitorName());
            // 设置头像url
            addVisitor.setAvatarUrl(githubUserDTO.getAvatarUrl());
            // 设置首次登录时间
            addVisitor.setGmtCreate(System.currentTimeMillis());
            // 设置最近更新时间
            addVisitor.setGmtModified(System.currentTimeMillis());
            // 执行insert语句
            int isAdd = visitorMapper.insert(addVisitor);

            // 判断是否insert成功
            if (isAdd != 1) throw new CustomizeException(CustomizeErrorCode.ADD_USER_ERROR);
            // 在结果集中设置登录用户id
            resultMap.put("visitor_id", addVisitor.getId());
        }
        // 通过jwtUtil设置token
        HashMap<String, String> visitorMap = new HashMap<>();
        visitorMap.put("visitor_name", githubUserDTO.getVisitorName());
        visitorMap.put("account_id", githubUserDTO.getAccountId() + "");
        visitorMap.put("avatar_url", githubUserDTO.getAvatarUrl());
        // 生成token
        String token = JwtUtil.createToken(visitorMap);
        resultMap.put("token", token);
        return resultMap;
    }
}
