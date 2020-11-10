package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.AddTagDTO;
import com.markloy.markblog.dto.UpdateTagDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.TagMapper;
import com.markloy.markblog.pojo.Tag;
import com.markloy.markblog.pojo.TagExample;
import com.markloy.markblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 查询所有标签
     * @return
     */
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
            tagMap.put("gmt_create", tag.getGmtCreate());
            tagMap.put("gmt_modified", tag.getGmtModified());

            tagList.add(tagMap);
        });
        //封装结果集
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tags", tagList);
        return resultMap;
    }

    /**
     * 添加标签
     * @param addTagDTO
     * @return
     */
    @Override
    public Map<String, Object> addTag(AddTagDTO addTagDTO) {
        Tag record = new Tag();
        // 设置标签名
        record.setTagName(addTagDTO.getTagName());
        // 设置创建时间
        record.setGmtCreate(System.currentTimeMillis());
        // 设置修改时间
        record.setGmtModified(record.getGmtCreate());
        // 执行insert语句
        int isInsert = tagMapper.insertSelective(record);
        if (isInsert != 1) {
            throw new CustomizeException(CustomizeErrorCode.ADD_TAG_ERROR);
        }
        // 返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tag", record);
        return resultMap;
    }

    /**
     * 修改标签
     * @param updateTagDTO
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> updateTag(UpdateTagDTO updateTagDTO) {
        // 查询库中是否存在该标签
        Tag tag = tagMapper.selectByPrimaryKey(updateTagDTO.getId());
        if (tag == null) {
            throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
        }
        Tag record = new Tag();
        // 设置分类id
        record.setId(updateTagDTO.getId());
        // 设置分类名
        record.setTagName(updateTagDTO.getTagName());
        // 设置修改时间
        record.setGmtModified(System.currentTimeMillis());
        // 执行update
        int isUpdate = tagMapper.updateByPrimaryKeySelective(record);
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_TAG_ERROR);
        }
        // 返回结果
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("tag", record);
        return resultMap;
    }

    /**
     * 删除标签
     * @param id 标签id
     */
    @Transactional
    @Override
    public void deleteTag(Integer id) {
        // 查询库中该标签
        Tag tag = tagMapper.selectByPrimaryKey(id);
        if (tag == null) {
            throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
        }
        // 存在该标签，再删除
        int isDelete = tagMapper.deleteByPrimaryKey(id);
        if (isDelete != 1) {
            throw new CustomizeException(CustomizeErrorCode.DELETE_TAG_ERROR);
        }
    }
}
