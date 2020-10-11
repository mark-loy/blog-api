package com.markloy.markblog.mapper;

import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
}
