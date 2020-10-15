package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryExtMapper {


    List<Category> findByLimit(Integer count);

}
