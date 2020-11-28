package com.markloy.markblog.controller;

import com.markloy.markblog.dto.*;
import com.markloy.markblog.provider.OSSProvider;
import com.markloy.markblog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客后台管理api
 */
@RestController
@RequestMapping("/private/api/back")
@Slf4j
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
     *
     * @return
     */
    @GetMapping("/article")
    public ResultDTO findArticleList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "offset", defaultValue = "10") Integer offset,
                                     @RequestParam(value = "cate_id", defaultValue = "") Integer cateId,
                                     @RequestParam(value = "tag_id", defaultValue = "") Integer tagId,
                                     @RequestParam(value = "search", defaultValue = "") String search) {
        //文章数据加载
        Map<String, Object> articleByPage = articleService.findArticleByPage(currentPage, offset, search, cateId, tagId);
        return ResultDTO.success(articleByPage);
    }

    /**
     * 添加文章api
     *
     * @return
     */
    @PostMapping("/article")
    public ResultDTO addArticle(@Validated @RequestBody AddArticleDTO articleDTO) {
        Map<String, Object> map = articleService.addArticle(articleDTO);
        return ResultDTO.success(map);
    }

    /**
     * 修改文章api
     *
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
     *
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
     *
     * @return
     */
    @GetMapping("/category")
    public ResultDTO findAllCategory() {

        Map<String, Object> map = categoryService.getFindAll();

        return ResultDTO.success(map);
    }

    /**
     * 添加分类api
     *
     * @return
     */
    @PostMapping("/category")
    public ResultDTO addCategory(@RequestBody() @Validated AddCategoryDTO addCategoryDTO) {

        Map<String, Object> map = categoryService.addCategory(addCategoryDTO);

        return ResultDTO.success(map);
    }

    /**
     * 修改分类api
     *
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
     *
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
     *
     * @return
     */
    @GetMapping("/tags")
    public ResultDTO findAllTags() {

        Map<String, Object> map = tagService.getFindAll();

        return ResultDTO.success(map);
    }

    /**
     * 添加标签api
     *
     * @param addTagDTO
     * @return
     */
    @PostMapping("/tags")
    public ResultDTO addTag(@RequestBody @Validated AddTagDTO addTagDTO) {

        Map<String, Object> map = tagService.addTag(addTagDTO);

        return ResultDTO.success(map);
    }

    /**
     * 修改标签api
     *
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
     *
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
     *
     * @return
     */
    @GetMapping("/visitor")
    public ResultDTO findAllVisitor() {

        Map<String, Object> map = userService.findAllVisitor();

        return ResultDTO.success(map);
    }

    /**
     * 修改访客账户的状态
     *
     * @param id    访客账户id
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

    @Autowired
    private OSSProvider ossProvider;

    /**
     * 文件上传api
     *
     * @param request 请求
     * @return
     * @throws IOException io异常
     */
    @ResponseBody
    @PostMapping("/file/upload")
    public ResultDTO fileUpload(HttpServletRequest request) throws Exception {
        //转化request对象为上传文件的request对象
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取文件
        MultipartFile parameter = multipartRequest.getFile("file");
        assert parameter != null;
        Map<String, Object> map = ossProvider.fileUpload(parameter);
        return ResultDTO.success(map);
    }

    /**
     * 移除单个文件api
     *
     * @param fileName 文件名
     * @return
     */
    @DeleteMapping("/file/delete/{file_name}")
    public ResultDTO fileDelete(@PathVariable("file_name") String fileName) throws Exception {

        ossProvider.fileDelete(fileName);

        return ResultDTO.success(null);
    }

    /**
     * 查询管理员api
     *
     * @return
     */
    @GetMapping("/admin")
    public ResultDTO findAllAdmin() {

        Map<String, Object> map = userService.findAllAdmin();

        return ResultDTO.success(map);
    }

    /**
     * 根据id修改管理员信息api
     *
     * @param updateAdminDTO 修改实体DTO
     * @return
     */
    @PutMapping("/admin")
    public ResultDTO updateAdmin(@RequestBody @Validated UpdateAdminDTO updateAdminDTO) {

        Map<String, Object> map = userService.updateAdmin(updateAdminDTO);

        return ResultDTO.success(map);
    }

    /**
     * 修改访客账户的状态
     *
     * @param id    访客账户id
     * @param state 访客账户状态
     * @return
     */
    @PutMapping("/admin/{id}")
    public ResultDTO updateAdminState(
            @PathVariable("id") Integer id,
            @RequestParam(value = "state") Boolean state
    ) {

        Map<String, Object> map = userService.updateAdminState(id, state);

        return ResultDTO.success(map);
    }

    @Autowired
    private InformService informService;

    /**
     * 查询未读通知api
     * @return
     */
    @GetMapping("/inform")
    public ResultDTO findInformByState() {

        Map<String, Object> map = informService.findInformByState();

        return  ResultDTO.success(map);
    }

    @Autowired
    private MessageService messageService;

    /**
     * 分页查询留言记录api
     * @param currentPage 当前页
     * @param offset 当页显示数
     * @return
     */
    @GetMapping("/message")
    public ResultDTO findAllMessage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                    @RequestParam(value = "offset",defaultValue = "5") Integer offset) {

        List<Map<String, Object>> allMessage = messageService.findAllMessage(currentPage, offset, 0);
        long total = messageService.countMessage(0);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("messages", allMessage);
        resultMap.put("total", total);
        return ResultDTO.success(resultMap);
    }

    /**
     * 修改留言状态api
     * @param id 留言id
     * @return
     */
    @PutMapping("/message/{id}/{state}")
    public ResultDTO updateMessageState(@PathVariable("id") Integer id, @PathVariable("state") Boolean state) {

        Map<String, Object> resultMap = messageService.updateMessageState(id, state);

        return  ResultDTO.success(resultMap);
    }

}
