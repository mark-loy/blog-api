package com.markloy.markblog.controller;

import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/private/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/all")
    public ResultDTO findAll() {

        Map<String, Object> map = tagService.getFindAll();

        return ResultDTO.success(map);
    }

}
