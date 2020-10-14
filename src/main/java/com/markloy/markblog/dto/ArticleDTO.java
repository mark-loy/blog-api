package com.markloy.markblog.dto;

import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.Tag;
import com.markloy.markblog.pojo.User;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO {

    private Integer id;
    private String title;
    private String description;
    private String context;
    private Integer viewCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private UserDTO userDTO;
    private Category category;
    private List<Tag> tags;
}
