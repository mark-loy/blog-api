package com.markloy.markblog.service.impl;

import com.markloy.markblog.mapper.TagMapper;
import com.markloy.markblog.pojo.Tag;
import com.markloy.markblog.pojo.TagExample;
import com.markloy.markblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Map<String, Object> getFindAll() {
        //查询所有的tag标签信息
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        //遍历结果集
        List<Map<String, Object>> tagList = new ArrayList<>();
        tags.forEach(tag -> {
            HashMap<String, Object> tagMap = new HashMap<>();
            tagMap.put("id", tag.getId());
            tagMap.put("tag_name", tag.getTagName());
            tagMap.put("article_count", tag.getArticleCount());
            tagList.add(tagMap);
        });
        //封装结果集
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tags", tagList);
        return resultMap;
    }
}
