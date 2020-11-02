package com.markloy.markblog.service;

import com.markloy.markblog.dto.AddCategoryDTO;
import com.markloy.markblog.dto.UpdateCategoryDTO;

import java.util.Map;

public interface CategoryService {

    Map<String, Object> getFindAll();

    Map<String, Object> addCategory(AddCategoryDTO addCategoryDTO);

    Map<String, Object> updateCategory(UpdateCategoryDTO updateCategoryDTO);

    void deleteCategory(Integer id);
}
