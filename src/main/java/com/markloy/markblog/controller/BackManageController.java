package com.markloy.markblog.controller;

import com.markloy.markblog.dto.*;
import com.markloy.markblog.service.ArticleService;
import com.markloy.markblog.service.CategoryService;
import com.markloy.markblog.service.TagService;
import com.markloy.markblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 博客后台管理api
 */
@RestController
@RequestMapping("/back")
public class BackManageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    /**
     * 查询文章列表api
     * @return
     */
    @GetMapping("/article")
    public ResultDTO findArticleList(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "offset",defaultValue = "10") Integer offset,
                                     @RequestParam(value = "cate_id",defaultValue = "") Integer cateId,
                                     @RequestParam(value = "tag_id",defaultValue = "") Integer tagId,
                                     @RequestParam(value = "search", defaultValue = "") String search) {
        //文章数据加载
        Map<String, Object> articleByPage = articleService.findArticleByPage(currentPage, offset, search, cateId, tagId);
        return ResultDTO.success(articleByPage);
    }

    /**
     * 添加文章api
     * @return
     */
    @PostMapping("/article")
    public ResultDTO addArticle(@Validated @RequestBody AddArticleDTO articleDTO) {
        Map<String, Object> map = articleService.addArticle(articleDTO);
        return ResultDTO.success(map);
    }

    /**
     * 修改文章api
     * @param articleDTO
     * @return
     */
    @PutMapping("/article")
    public ResultDTO updateArticle(@RequestBody UpdateArticleDTO articleDTO) {

        Map<String, Object> map = articleService.updateArticle(articleDTO);

        return ResultDTO.success(map);
    }

    /**
     * 删除文章api
     * @param id
     * @return
     */
    @DeleteMapping("/article/{id}")
    public ResultDTO deleteArticle(@PathVariable("id") Integer id) {

        articleService.deleteArticle(id);

        return ResultDTO.success(null);
    }

    /**
     * 查询所有分类api
     * @return
     */
    @GetMapping("/category")
    public ResultDTO findAllCategory() {

        Map<String, Object> map = categoryService.getFindAll();

        return ResultDTO.success(map);
    }

    /**
     * 添加分类api
     * @return
     */
    @PostMapping("/category")
    public ResultDTO addCategory(@RequestBody() @Validated AddCategoryDTO addCategoryDTO) {

        Map<String, Object> map = categoryService.addCategory(addCategoryDTO);

        return ResultDTO.success(map);
    }

    /**
     * 修改分类api
     * @param updateCategoryDTO
     * @return
     */
    @PutMapping("/category")
    public ResultDTO updateCategory(@RequestBody() @Validated UpdateCategoryDTO updateCategoryDTO) {

        Map<String, Object> map = categoryService.updateCategory(updateCategoryDTO);

        return ResultDTO.success(map);
    }

    /**
     * 根据id删除分类api
     * @param id
     * @return
     */
    @DeleteMapping("/category/{id}")
    public ResultDTO deleteCategory(@PathVariable("id") Integer id) {

        categoryService.deleteCategory(id);

        return ResultDTO.success(null);
    }

    /**
     * 查询所有的标签api
     * @return
     */
    @GetMapping("/tags")
    public ResultDTO findAllTags() {

        Map<String, Object> map = tagService.getFindAll();

        return ResultDTO.success(map);
    }

    /**
     * 添加标签api
     * @param addTagDTO
     * @return
     */
    @PostMapping("/tags")
    public ResultDTO addTag(@RequestBody @Validated AddTagDTO addTagDTO) {

        Map<String, Object> map =  tagService.addTag(addTagDTO);

        return ResultDTO.success(map);
    }

    /**
     * 修改标签api
     * @param updateTagDTO
     * @return
     */
    @PutMapping("/tags")
    public ResultDTO updateTag(@RequestBody @Validated UpdateTagDTO updateTagDTO) {

        Map<String, Object> map = tagService.updateTag(updateTagDTO);

        return ResultDTO.success(map);
    }

    /**
     * 根据id删除标签api
     * @param id
     * @return
     */
    @DeleteMapping("/tags/{id}")
    public ResultDTO deleteTag(@PathVariable("id") Integer id) {

        tagService.deleteTag(id);

        return ResultDTO.success(null);
    }

    /**
     * 查询所有的访客信息
     * @return
     */
    @GetMapping("/visitor")
    public ResultDTO findAllVisitor() {

        Map<String, Object> map = userService.findAllVisitor();

        return ResultDTO.success(map);
    }

    /**
     * 修改访客账户的状态
     * @param id 访客账户id
     * @param state 访客账户状态
     * @return
     */
    @PutMapping("/visitor/{id}")
    public ResultDTO updateVisitorState(
            @PathVariable("id") Integer id,
            @RequestParam(value = "state") Boolean state
    ) {

        Map<String, Object> map = userService.updateVisitorState(id, state);

        return ResultDTO.success(map);
    }


}
