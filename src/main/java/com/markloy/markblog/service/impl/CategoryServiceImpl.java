package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.AddCategoryDTO;
import com.markloy.markblog.dto.UpdateCategoryDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.CategoryMapper;
import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.CategoryExample;
import com.markloy.markblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询所有分类数据
     * @return
     */
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
            map.put("gmt_create", category.getGmtCreate());
            map.put("gmt_modified", category.getGmtModified());
            cateList.add(map);
        });
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("category", cateList);
        return resultMap;
    }

    /**
     * 添加分类
     * @param addCategoryDTO
     * @return
     */
    @Override
    public Map<String, Object> addCategory(AddCategoryDTO addCategoryDTO) {
        // 设置需要插入数据的字段
        Category record = new Category();
        // 设置分类名
        record.setCategoryName(addCategoryDTO.getCateName());
        // 设置创建时间
        record.setGmtCreate(System.currentTimeMillis());
        // 设置修改时间
        record.setGmtModified(record.getGmtCreate());
        // 执行insert
        int isAddSuccess = categoryMapper.insertSelective(record);
        if (isAddSuccess != 1) {
            throw new CustomizeException(CustomizeErrorCode.ADD_CATEGORY_ERROR);
        }
        // 结果返回
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("category", record);
        return resultMap;
    }

    /**
     * 根据id修改分类
     * @param updateCategoryDTO
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> updateCategory(UpdateCategoryDTO updateCategoryDTO) {
        // 查询库中是否存在该id
        Category category = categoryMapper.selectByPrimaryKey(updateCategoryDTO.getId());
        if (category == null) {
            throw new CustomizeException(CustomizeErrorCode.CATEGORY_NOT_FOUND);
        }

        // 存在分类，则修改
        Category record = new Category();
        // 设置分类id
        record.setId(updateCategoryDTO.getId());
        // 设置新的分类名
        record.setCategoryName(updateCategoryDTO.getCateName());
        // 设置修改时间
        record.setGmtModified(System.currentTimeMillis());
        // 执行update
        int isUpdate = categoryMapper.updateByPrimaryKeySelective(record);
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_CATEGORY_ERROR);
        }

        // 返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("category", record);
        return resultMap;
    }

    /**
     * 删除分类
     * @param id
     */
    @Transactional
    @Override
    public void deleteCategory(Integer id) {
        // 查询库中是否存在该分类
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            throw new CustomizeException(CustomizeErrorCode.CATEGORY_NOT_FOUND);
        }
        // 存在，删除该分类
        int isDelete = categoryMapper.deleteByPrimaryKey(id);
        if (isDelete != 1) {
            throw new CustomizeException(CustomizeErrorCode.DELETE_CATEGORY_ERROR);
        }

    }
}
