package com.markloy.markblog.service.impl;

import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.ArticleMapper;
import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findArticleByPage(int currentPage, int offset) {
        if (currentPage < 1)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        int totalCount = articleMapper.countArticle();
        int totalPage;
        if (totalCount > 0) {
            if (totalCount % offset == 0) {
                totalPage = totalCount / offset;
            }else {
                totalPage = totalCount / offset + 1;
            }
        } else {
            totalPage = 1;
        }
        if (currentPage > totalPage)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        //计算查询的数据下标
        int count = (currentPage - 1) * currentPage;
        return articleMapper.findArticleByPage(count, offset);
    }
}
