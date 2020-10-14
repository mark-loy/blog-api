package com.markloy.markblog.mapper;

import com.markloy.markblog.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {

    @Select("select * from article " +
            "where title regexp '#{search}' " +
            "order by gmt_create desc " +
            "limit #{count}, #{offset}")
    List<Article> findArticleByPageSearch(@Param("count") Integer count,
                                    @Param("offset") Integer offset,
                                    @Param("search") String search);

    @Select("select * from article " +
            "order by gmt_create desc " +
            "limit #{count}, #{offset}")
    List<Article> findArticleByPage(@Param("count") Integer count,
                                    @Param("offset") Integer offset);

    @Select("select count(*) from article")
    int countArticle();

    @Select("select * from article where id=#{id}")
    Article findArticleById(@Param("id") Integer id);

}
