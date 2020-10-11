package com.markloy.markblog.service;

import com.markloy.markblog.pojo.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findArticleByPage(int currentPage, int offset);
}
