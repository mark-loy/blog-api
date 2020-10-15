package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.ArticleDTO;
import com.markloy.markblog.dto.ArticleSearchDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.*;
import com.markloy.markblog.pojo.*;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleExtMapper articleExtMapper;

    /**
     * 获取文章列表
     *
     * @param currentPage 当前页
     * @param offset      当页显示数
     * @return 文章列表
     */
    @Override
    public Map<String, Object> findArticleByPage(Integer currentPage, Integer offset, String search, Integer categoryId, Integer tagId) {
        //判断当前页是否小于1
        if (currentPage < 1)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);

        //实例查询条件
        ArticleSearchDTO searchDTO = new ArticleSearchDTO();
        //处理search
        String toSearch = toSearch(search);
        //设置search
        searchDTO.setSearch(toSearch);
        //设置分类id
        searchDTO.setCategoryId(categoryId);
        //设置标签id
        searchDTO.setTagId(tagId);

        //获取文章总数
        int totalCount = articleExtMapper.countByPageOrSearch(searchDTO);

        int totalPage = 1;
        //判断文章总数，计算总页数
        if (totalCount > 0) {
            if (totalCount % offset == 0) {
                totalPage = totalCount / offset;
            } else {
                totalPage = totalCount / offset + 1;
            }
        }
        //判断当前页是否大于总页数
        if (currentPage > totalPage)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        //计算查询的数据下标
        int count = (currentPage - 1) * offset;

        //设置起始查询下标
        searchDTO.setCount(count);
        //设置当页显示数
        searchDTO.setSize(offset);

        //查询文章信息
        List<Article> articleByPage = articleExtMapper.findByPageOrSearch(searchDTO);
        //遍历文章列表
        List<Map<String, Object>> articleList = forArticle(articleByPage);
        HashMap<String, Object> map = new HashMap<>();
        map.put("articles", articleList);
        map.put("total", totalCount);
        return map;
    }

    /**
     * 遍历文章信息,结果集统一封装
     *
     * @param articleByPage
     * @return
     */
    public List<Map<String, Object>> forArticle(List<Article> articleByPage) {
        List<Map<String, Object>> articleList = new ArrayList<>();

        articleByPage.forEach(article -> {
            //获取当前文章发表用户信息
            User user = userMapper.selectByPrimaryKey(article.getUserId());
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("petName", user.getPetName());
            userMap.put("avatar", user.getAvatar());
            //获取当前文章的分类信息
            Category category = categoryMapper.selectByPrimaryKey(article.getCategoryId());
            HashMap<String, Object> cateMap = new HashMap<>();
            cateMap.put("id", category.getId());
            cateMap.put("cate_name", category.getCategoryName());
            //数据库中存储的标签id是：1,2,3 ;需要转化该id
            String[] tagIds = article.getTagsId().split(",");
            List<Integer> idList = new ArrayList<>();
            for (String id : tagIds) {
                idList.add(Integer.parseInt(id));
            }
            //获取当前文章的标签信息;
            TagExample tagExample = new TagExample();
            tagExample.createCriteria().andIdIn(idList);
            List<Tag> tagList = tagMapper.selectByExample(tagExample);
            List<Map<String, Object>> tagsList = new ArrayList<>();
            //遍历标签，获取需要的标签信息
            tagList.forEach(tag -> {
                Map<String, Object> tagMap = new HashMap<>();
                tagMap.put("id", tag.getId());
                tagMap.put("tag_name", tag.getTagName());
                tagMap.put("article_count", tag.getArticleCount());
                tagsList.add(tagMap);
            });
            //文章信息统一封装
            HashMap<String, Object> articleMap = new HashMap<>();
            articleMap.put("id", article.getId());
            articleMap.put("title", article.getTitle());
            articleMap.put("description", article.getDescription());
            articleMap.put("view_count", article.getViewCount());
            articleMap.put("gmt_create", article.getGmtCreate());
            //结果集统一封装
            HashMap<String, Object> articleInfo = new HashMap<>();
            articleInfo.put("user", userMap);
            articleInfo.put("category", cateMap);
            articleInfo.put("article", articleMap);
            articleInfo.put("tag", tagsList);
            articleList.add(articleInfo);
        });
        return articleList;
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @Override
    public ArticleDTO findArticleById(Integer id) {


        return null;
    }

    /**
     * 格式化search
     * 格式为：linux|spring|java
     *
     * @param search
     * @return
     */
    public String toSearch(String search) {
        //清除左右空格，将search内容所有空格替换为| 比如：Linux|||spring||java
        String replace = search.trim().replace(" ", "|");
        //将search中的每个字符拆分
        String[] split = replace.split("|");
        //遍历，过滤多余的|
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            if (list.isEmpty()) {
                list.add(s);
            } else {
                int size = list.size();
                if (list.get(size - 1).equals("|")) {
                    if (!"|".equals(s)) {
                        list.add(s);
                    }
                } else {
                    if (!"|".equals(s)) {
                        list.add(s);
                    } else {
                        list.add("|");
                    }
                }
            }
        }
        StringBuilder searchTmp = new StringBuilder();
        for (String item : list) {
            searchTmp.append(item);
        }
        return searchTmp.toString();
    }
}
