package com.markloy.markblog.dto;

import com.markloy.markblog.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class HomeDTO {

    private List<Article> article;

}
