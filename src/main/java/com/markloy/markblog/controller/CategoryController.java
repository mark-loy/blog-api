package com.markloy.markblog.controller;

import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResultDTO category() {
        //获取所有的分类数据
        Map<String, Object> map = categoryService.getFindAll();
        return ResultDTO.success(map);
    }

}
