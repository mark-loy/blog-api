package com.markloy.markblog.controller;

import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.mapper.CategoryMapper;
import com.markloy.markblog.mapper.TagMapper;
import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.Tag;
import com.markloy.markblog.pojo.User;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * home页面数据请求
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    /**
     * 首页，文章列表
     * @param currentPage
     * @param offset
     * @return
     */
    @GetMapping("/article")
    public ResultDTO article(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "offset",defaultValue = "10") Integer offset,
                             @RequestParam(value = "search", defaultValue = "") String search) {
        //文章数据加载
        Map<String, Object> articleByPage = articleService.findArticleByPage(currentPage, offset, search);

        return ResultDTO.success(articleByPage);
    }

    /**
     * 首页，other信息
     * @return
     */
    @GetMapping("/otherInfo")
    public ResultDTO otherInfo() {
        //获取用户信息（头像、昵称）
        User user = userMapper.findById(1);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        //获取分类总数
        Integer countCate = categoryMapper.countCate();
        //获取标签总数
        Integer countTag = tagMapper.countTag();
        //获取分类文章前三的分类
        List<Category> cateTop = categoryMapper.findTop();
        //获取标签文章前十的标签
        List<Tag> tagTop = tagMapper.findTop();
        //结果集统一封装
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", userDTO);
        hashMap.put("countCate", countCate);
        hashMap.put("countTag", countTag);
        hashMap.put("cateTop", cateTop);
        hashMap.put("tagTop", tagTop);
        return ResultDTO.success(hashMap);
    }

}
