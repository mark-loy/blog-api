package com.markloy.markblog.mapper;

import com.markloy.markblog.dto.ArticleSearchDTO;
import com.markloy.markblog.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleExtMapper {

    /**
     * 查询文章并实现分页
     * @return
     */
    List<Article> findByPageOrSearch(ArticleSearchDTO searchDTO);

    /**
     * 查询文章总记录数
     * @param searchDTO
     * @return
     */
    Integer countByPageOrSearch(ArticleSearchDTO searchDTO);

}
