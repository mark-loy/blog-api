package com.markloy.markblog.controller;

import com.markloy.markblog.dto.HomeDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/home")
    public ResultDTO home(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                          @RequestParam(value = "offset",defaultValue = "10") int offset) {
        //文章数据加载
        List<Article> articles = articleService.findArticleByPage(currentPage, offset);


        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setArticle(articles);
        return ResultDTO.success(articles);
    }

}
