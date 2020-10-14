package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.CategoryDTO;
import com.markloy.markblog.mapper.ArticleMapper;
import com.markloy.markblog.mapper.CategoryMapper;
import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Map<String, Object> getFindAll() {
        //查询分类数据
        List<Category> categoryList = categoryMapper.findAll();
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryList, categoryDTO);
        HashMap<String, Object> map = new HashMap<>();
        map.put("category", categoryDTO);
        return map;
    }
}
