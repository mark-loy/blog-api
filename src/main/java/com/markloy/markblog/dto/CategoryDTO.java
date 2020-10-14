package com.markloy.markblog.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private Integer id;
    private String categoryName;
    private Integer articleCount;
    private Long gmtCreate;
    private Long gmtModified;

}
