package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagExtMapper {

    List<Tag> findByLimit(Integer count);

    int incrArticleCount(Integer id);

    int decrArticleCount(Integer id);
}
