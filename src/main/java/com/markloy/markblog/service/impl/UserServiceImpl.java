package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UpdateAdminDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.dto.VisitorLoginDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.AdminMapper;
import com.markloy.markblog.mapper.VisitorMapper;
import com.markloy.markblog.pojo.*;
import com.markloy.markblog.service.UserService;
import com.markloy.markblog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminMapper adminMapper;

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
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(loginDTO.getUsername());
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        //判断用户是否存在
        if (adminList.size() == 0)
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        //对密码进行md5加密
        String passwordEncode = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        //获取查询到的用户
        Admin admin = adminList.get(0);
        //判断密码是否正确
        if (!admin.getPassword().equals(passwordEncode)) {
            throw new CustomizeException(CustomizeErrorCode.PASSWORD_ERROR);
        }
        //判断账号状态是否正常
        if (!admin.getState()) {
            // 账号异常，禁止登录
            throw new CustomizeException(CustomizeErrorCode.ACCOUNT_ERROR);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(admin, userDTO);
        return userDTO;
    }

    /**
     * 保存GitHub登录的访客信息
     * @param visitorLoginDTO
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> saveVisitorUser(VisitorLoginDTO visitorLoginDTO, int type) {
        // 通过accountId查询该用户是否存在
        VisitorExample visitorExample = new VisitorExample();
        visitorExample.createCriteria()
                .andAccountIdEqualTo(visitorLoginDTO.getAccountId());
        List<Visitor> visitors = visitorMapper.selectByExample(visitorExample);
        // 结果集
        HashMap<String, Object> resultMap = new HashMap<>();
        // 判断当前是否为新用户
        if (visitors.size() == 1) {
            // 判断用户状态是否为正常
            if (!visitors.get(0).getState()) {
                // 账号异常，禁止登录
                throw new CustomizeException(CustomizeErrorCode.ACCOUNT_ERROR);
            }
            // 存在该用户，则更新该用户信息
            Visitor updateVisitor = new Visitor();
            // 设置主键
            updateVisitor.setId(visitors.get(0).getId());
            // 设置访客名
            updateVisitor.setVisitorName(visitorLoginDTO.getVisitorName());
            // 设置头像url
            updateVisitor.setAvatarUrl(visitorLoginDTO.getAvatarUrl());
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
            addVisitor.setAccountId(visitorLoginDTO.getAccountId());
            // 设置登录来源
            addVisitor.setSource(type);
            // 设置访客名
            addVisitor.setVisitorName(visitorLoginDTO.getVisitorName());
            // 设置头像url
            addVisitor.setAvatarUrl(visitorLoginDTO.getAvatarUrl());
            // 设置首次登录时间
            addVisitor.setGmtCreate(System.currentTimeMillis());
            // 设置最近更新时间
            addVisitor.setGmtModified(System.currentTimeMillis());
            // 执行insert语句
            int isAdd = visitorMapper.insertSelective(addVisitor);

            // 判断是否insert成功
            if (isAdd != 1) throw new CustomizeException(CustomizeErrorCode.ADD_USER_ERROR);
            // 在结果集中设置登录用户id
            resultMap.put("visitor_id", addVisitor.getId());
        }
        // 通过jwtUtil设置token
        HashMap<String, String> visitorMap = new HashMap<>();
        visitorMap.put("visitor_name", visitorLoginDTO.getVisitorName());
        visitorMap.put("account_id", visitorLoginDTO.getAccountId() + "");
        visitorMap.put("avatar_url", visitorLoginDTO.getAvatarUrl());
        // 生成token
        String token = JwtUtil.createToken(visitorMap);
        resultMap.put("token", token);
        return resultMap;
    }

    /**
     * 查询所有的访客信息
     * @return
     */
    @Override
    public Map<String, Object> findAllVisitor() {
        List<Visitor> visitors = visitorMapper.selectByExample(new VisitorExample());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("visitors", visitors);
        return resultMap;
    }

    /**
     * 修改访客账户状态
     * @param id 账户id
     * @param state 账户状态
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> updateVisitorState(Integer id, Boolean state) {
        // 查询库中是否存在该访客
        Visitor visitor = visitorMapper.selectByPrimaryKey(id);
        if (visitor == null) {
            throw new CustomizeException(CustomizeErrorCode.VISITOR_NOT_FOUND);
        }
        // 访客存在，修改状态
        Visitor record = new Visitor();
        // 设置修改的账户id
        record.setId(id);
        // 设置要修改的状态
        record.setState(state);
        int isUpdate = visitorMapper.updateByPrimaryKeySelective(record);
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_USER_ERROR);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("visitor", record);
        return resultMap;
    }

    /**
     * 查询所有管理员信息
     * @return
     */
    @Override
    public Map<String, Object> findAllAdmin() {
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("admins", adminList);
        return resultMap;
    }

    /**
     * 修改管理员信息
     * @param updateAdminDTO 修改字段DTO
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> updateAdmin(UpdateAdminDTO updateAdminDTO) {
        // 查询库中是否存在
        Admin admin = adminMapper.selectByPrimaryKey(updateAdminDTO.getId());
        if (admin == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        }
        // 存在，修改信息, 设置修改字段
        Admin record = new Admin();
        // 设置主键
        record.setId(updateAdminDTO.getId());
        // 设置用户名
        record.setUsername(updateAdminDTO.getUsername());
        // 设置昵称
        record.setPetName(updateAdminDTO.getPetName());
        // 设置邮箱
        record.setEmail(updateAdminDTO.getEmail());
        // 设置头像
        record.setAvatar(updateAdminDTO.getAvatar());
        // 设置更新时间
        record.setGmtModified(System.currentTimeMillis());
        // 执行update
        int isUpdate = adminMapper.updateByPrimaryKeySelective(record);
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_ADMIN_ERROR);
        }
        // 结果返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("admin", record);
        return resultMap;
    }

    /**
     * 修改管理员状态
     * @param id 主键
     * @param state 状态
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> updateAdminState(Integer id, Boolean state) {
        // 查询该管理员信息是否存在
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
        }
        // 存在，修改状态
        Admin record = new Admin();
        // 设置主键
        record.setId(id);
        // 设置状态
        record.setState(state);
        // 执行update
        int isUpdate = adminMapper.updateByPrimaryKeySelective(record);
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_ADMIN_ERROR);
        }
        // 结果处理
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("admin", record);
        return resultMap;
    }
}
