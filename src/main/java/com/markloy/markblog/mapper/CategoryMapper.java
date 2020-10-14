package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> findAll();

    @Select("select id, category_name from category where id=#{id}")
    Category findById(@Param("id") Integer categoryId);

    @Select("select count(*) from category")
    Integer countCate();

    @Select("select id, category_name,article_count from category order by article_count desc limit 3")
    List<Category> findTop();
}
