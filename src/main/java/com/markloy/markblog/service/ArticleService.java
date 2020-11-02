package com.markloy.markblog.service;

import com.markloy.markblog.dto.AddArticleDTO;
import com.markloy.markblog.dto.ArticleDTO;
import com.markloy.markblog.dto.ArticleLikeDTO;
import com.markloy.markblog.dto.UpdateArticleDTO;

import java.util.Map;

public interface ArticleService {

    Map<String, Object> findArticleByPage(Integer currentPage, Integer offset, String search, Integer categoryId, Integer tagId);

    Map<String, Object> findArticleDetail(Integer id);

    Map<String, Object> addArticle(AddArticleDTO articleDTO);

    Map<String, Object> updateArticle(UpdateArticleDTO articleDTO);

    void deleteArticle(Integer id);

    Map<String, Object> giveLike(ArticleLikeDTO articleLikeDTO);

    Map<String, Object> findArticleLike(Integer visitorId, Integer articleId);
}
