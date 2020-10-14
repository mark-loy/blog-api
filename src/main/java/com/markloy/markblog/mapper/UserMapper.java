package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("select id, username, pet_name, avatar, article_count from user where id=#{id}")
    User findById(@Param("id") Integer userId);
}
