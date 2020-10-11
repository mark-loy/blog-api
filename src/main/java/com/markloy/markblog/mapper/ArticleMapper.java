package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {

    @Select("select * from article limit #{count}, #{offset}")
    List<Article> findArticleByPage(@Param("count") int count,
                                    @Param("offset") int offset);

    @Select("select count(*) from article")
    int countArticle();

}
