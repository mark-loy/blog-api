package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.CategoryDTO;
import com.markloy.markblog.mapper.CategoryMapper;
import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.CategoryExample;
import com.markloy.markblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Category> categoryList = categoryMapper.selectByExample(new CategoryExample());
        List<Map<String, Object>> cateList = new ArrayList<>();
        categoryList.forEach(category -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", category.getId());
            map.put("cate_name", category.getCategoryName());
            map.put("article_count", category.getArticleCount());
            cateList.add(map);
        });
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("category", cateList);
        return resultMap;
    }
}
