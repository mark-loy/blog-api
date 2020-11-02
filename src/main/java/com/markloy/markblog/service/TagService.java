package com.markloy.markblog.service;

import com.markloy.markblog.dto.AddTagDTO;
import com.markloy.markblog.dto.UpdateTagDTO;

import java.util.Map;

public interface TagService {


    Map<String, Object> getFindAll();

    Map<String, Object> addTag(AddTagDTO addTagDTO);

    Map<String, Object> updateTag(UpdateTagDTO updateTagDTO);

    void deleteTag(Integer id);
}

