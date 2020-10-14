package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.ArticleDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.ArticleMapper;
import com.markloy.markblog.mapper.CategoryMapper;
import com.markloy.markblog.mapper.TagMapper;
import com.markloy.markblog.mapper.UserMapper;
import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.pojo.Category;
import com.markloy.markblog.pojo.Tag;
import com.markloy.markblog.pojo.User;
import com.markloy.markblog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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

    /**
     * 获取文章列表
     * @param currentPage 当前页
     * @param offset 当页显示数
     * @return 文章列表
     */
    @Override
    public Map<String, Object> findArticleByPage(Integer currentPage, Integer offset, String search) {
        //判断当前页是否小于1
        if (currentPage < 1)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        //获取文章总数
        int totalCount = articleMapper.countArticle();
        int totalPage = 1;
        //判断文章总数，计算总页数
        if (totalCount > 0) {
            if (totalCount % offset == 0) {
                totalPage = totalCount / offset;
            }else {
                totalPage = totalCount / offset + 1;
            }
        }
        //判断当前页是否大于总页数
        if (currentPage > totalPage)
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        //计算查询的数据下标
        int count = (currentPage - 1) * offset;
        List<Article> articleByPage;
        //处理search
        if (StringUtils.isEmpty(search.trim())) {
            String toSearch = toSearch(search);
            articleByPage = articleMapper.findArticleByPageSearch(count, offset, toSearch);
        } else {
            articleByPage = articleMapper.findArticleByPage(count, offset);
        }

        List<ArticleDTO> articleDTOS = new ArrayList<>();
        //遍历文章列表
        articleByPage.forEach(article -> {
            //获取当前文章发表用户信息
            User user = userMapper.findById(article.getUserId());
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            //获取当前文章的分类信息
            Category category = categoryMapper.findById(article.getCategoryId());
            //获取当前文章的标签列表
            List<Tag> tags = tagMapper.findByIds(article.getTagsId());
            //设置dto属性
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setUserDTO(userDTO);
            articleDTO.setCategory(category);
            articleDTO.setTags(tags);
            BeanUtils.copyProperties(article, articleDTO);
            articleDTOS.add(articleDTO);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("articleList", articleDTOS);
        map.put("total", totalCount);
        return map;
    }

    /**
     * 获取文章详情
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
                if ( list.get( size - 1).equals("|")) {
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
