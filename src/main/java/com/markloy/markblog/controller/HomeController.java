package com.markloy.markblog.controller;

import com.markloy.markblog.dto.ArticleLikeDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.mapper.*;
import com.markloy.markblog.pojo.*;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * home页面数据请求
 */
@RestController
@RequestMapping("/private/api/home")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryExtMapper categoryExtMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagExtMapper tagExtMapper;

    /**
     * 首页，文章列表
     * @param currentPage
     * @param offset
     * @return
     */
    @GetMapping("/article")
    public ResultDTO article(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "offset",defaultValue = "10") Integer offset,
                             @RequestParam(value = "cate_id",defaultValue = "") Integer cateId,
                             @RequestParam(value = "tag_id",defaultValue = "") Integer tagId,
                             @RequestParam(value = "search", defaultValue = "") String search) {
        //文章数据加载
        Map<String, Object> articleByPage = articleService.findArticleByPage(currentPage, offset, search, cateId, tagId);

        return ResultDTO.success(articleByPage);
    }

    /**
     * 首页，other信息
     * @return
     */
    @GetMapping("/otherInfo")
    public ResultDTO otherInfo(@RequestParam(value = "cateCount", defaultValue = "3") Integer cateCount,
                               @RequestParam(value = "tagCount", defaultValue = "10") Integer tagCount) {
        //获取用户信息（头像、昵称）
        Admin admin = adminMapper.selectByPrimaryKey(1);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(admin, userDTO);
        //获取分类总数
        long countCate = categoryMapper.countByExample(new CategoryExample());
        //获取标签总数
        long countTag = tagMapper.countByExample(new TagExample());
        //获取分类文章前三的分类
        List<Category> cate = categoryExtMapper.findByLimit(cateCount);
        //获取标签文章前十的标签
        List<Tag> tag = tagExtMapper.findByLimit(tagCount);
        //结果集统一封装
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("user", userDTO);
        hashMap.put("countCate", countCate);
        hashMap.put("countTag", countTag);
        hashMap.put("cate", cate);
        hashMap.put("tag", tag);
        return ResultDTO.success(hashMap);
    }

    /**
     * 文章详细信息查询
     * @param id 文章id
     * @return
     */
    @GetMapping("/article/{id}")
    public ResultDTO articleDetail(@PathVariable("id") Integer id,
                                   @RequestParam(value = "informId", defaultValue = "0") Integer informId) {

        Map<String, Object> map = articleService.findArticleDetail(id, informId);

        return ResultDTO.success(map);
    }


    /**
     * 文章点赞api
     * @return
     */
    @PutMapping("/like/article")
    public ResultDTO giveLike(@RequestBody @Validated ArticleLikeDTO articleLikeDTO) {

        Map<String, Object> map = articleService.giveLike(articleLikeDTO);

        return ResultDTO.success(map);
    }

    /**
     * 查询当前访客，当前文章是否点赞
     * @param visitorId
     * @param articleId
     * @return
     */
    @GetMapping("/like/article")
    public ResultDTO findArticleLike(@RequestParam("visitorId") Integer visitorId,
                                     @RequestParam("articleId") Integer articleId) {
        Map<String, Object> map = articleService.findArticleLike(visitorId, articleId);
        return ResultDTO.success(map);
    }

}
