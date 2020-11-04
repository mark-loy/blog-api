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

    /**
     * 增加文章点赞数
     * @param id 文章id
     * @return
     */
    Integer incrArticleLikeCount(Integer id);

    /**
     * 减少文章点赞数
     * @param id 文章id
     * @return
     */
    Integer decrArticleLikeCount(Integer id);

    /**
     * 增加文章浏览数
     * @param id
     * @return
     */
    int incrArticleViewCount(Integer id);

}
