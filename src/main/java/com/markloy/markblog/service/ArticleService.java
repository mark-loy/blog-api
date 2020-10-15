package com.markloy.markblog.service;

import com.markloy.markblog.dto.ArticleDTO;

import java.util.Map;

public interface ArticleService {

    Map<String, Object> findArticleByPage(Integer currentPage, Integer offset, String search, Integer categoryId, Integer tagId);

    ArticleDTO findArticleById(Integer id);
}
