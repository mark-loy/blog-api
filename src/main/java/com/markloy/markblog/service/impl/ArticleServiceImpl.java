package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.*;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.enums.InformType;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.*;
import com.markloy.markblog.pojo.*;
import com.markloy.markblog.service.ArticleService;
import com.markloy.markblog.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleExtMapper articleExtMapper;

    @Autowired
    private CategoryExtMapper categoryExtMapper;

    @Autowired
    private TagExtMapper tagExtMapper;

    @Autowired
    private VisitorLikeMapper visitorLikeMapper;

    @Autowired
    private InformServiceImpl informService;

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
            Admin admin = adminMapper.selectByPrimaryKey(article.getAdminId());
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("id", admin.getId());
            userMap.put("petName", admin.getPetName());
            userMap.put("avatar", admin.getAvatar());
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
            articleMap.put("context", article.getContext());
            articleMap.put("view_count", article.getViewCount());
            articleMap.put("like_count", article.getLikeCount());
            articleMap.put("gmt_create", article.getGmtCreate());
            articleMap.put("gmt_modified", article.getGmtModified());
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
     * 根据id查询文章详情信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> findArticleDetail(Integer id, Integer informId) {
        // 文章信息
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
        }
        // 增加当前文章浏览数
        int isIncrView = articleExtMapper.incrArticleViewCount(id);
        if (isIncrView != 1) {
            throw new CustomizeException(CustomizeErrorCode.INCR_VIEWCOUNT_ERROR);
        }
        // 修改通知状态
        informService.updateInformState(informId);
        // 用户信息
        Admin admin = adminMapper.selectByPrimaryKey(article.getAdminId());
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("id", admin.getId());
        userMap.put("pet_name", admin.getPetName());
        userMap.put("avatar", admin.getAvatar());
        // 分类信息
        Category category = categoryMapper.selectByPrimaryKey(article.getCategoryId());
        Map<String, Object> cateMap = new HashMap<>();
        cateMap.put("id", category.getId());
        cateMap.put("cate_name", category.getCategoryName());
        // 标签信息
        // 处理标签ids
        String[] tagIds= article.getTagsId().split(",");
        List<Integer> tagIdList = new ArrayList<>();
        for (String tagIdStr : tagIds) {
            tagIdList.add(Integer.parseInt(tagIdStr));
        }
        // 遍历标签结果集
        List<Map<String, Object>> tagList = new ArrayList<>();
        tagIdList.forEach(tagId -> {
            Tag tag = tagMapper.selectByPrimaryKey(tagId);
            Map<String, Object> tagMap = new HashMap<>();
            tagMap.put("id", tag.getId());
            tagMap.put("tag_name", tag.getTagName());
            tagList.add(tagMap);
        });
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("article", article);
        resultMap.put("user", userMap);
        resultMap.put("cate", cateMap);
        resultMap.put("tags", tagList);
        return resultMap;
    }

    /**
     * 添加文章
     * @param articleDTO
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> addArticle(AddArticleDTO articleDTO) {
        // 验证文章的关联数据是否存在
        verifyArticle(articleDTO.getUserId(), articleDTO.getCategoryId(), articleDTO.getTags());

        // 验证通过，设置需要插入数据的字段
        Article record = new Article();
        // 设置标题
        record.setTitle(articleDTO.getTitle());
        // 设置展示图url
        record.setShowImg(articleDTO.getShowImg());
        // 设置发表人
        record.setAdminId(articleDTO.getUserId());
        // 设置分类
        record.setCategoryId(articleDTO.getCategoryId());
        // 设置标签
        record.setTagsId(articleDTO.getTags());
        // 设置内容
        record.setContext(articleDTO.getContext());
        // 设置发表时间
        record.setGmtCreate(System.currentTimeMillis());
        // 设置修改时间
        record.setGmtModified(record.getGmtCreate());
        // 执行sql
        int isSuccess = articleMapper.insertSelective(record);
        if (isSuccess != 1) {
            throw new CustomizeException(CustomizeErrorCode.ADD_ARTICLE_ERROR);
        }

        // 增加当前分类的文章数量
        int isIncrCateCount = categoryExtMapper.incrArticleCount(articleDTO.getCategoryId());
        if (isIncrCateCount != 1) {
            throw new CustomizeException(CustomizeErrorCode.ADD_ARTICLE_ERROR);
        }

        // 增加当前标签的文章数量
        String[] tagArray = articleDTO.getTags().split(",");
        for (String tagId : tagArray) {
            int isIncrTagCount = tagExtMapper.incrArticleCount(Integer.parseInt(tagId));
            if (isIncrTagCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.ADD_ARTICLE_ERROR);
            }
        }

        // 添加成功，返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("article", record);
        return resultMap;
    }

    /**
     * 修改文章
     * @param articleDTO
     * @return
     */
    @Transactional
    @Override
    public Map<String, Object> updateArticle(UpdateArticleDTO articleDTO) {
        // 修改文章数据前，先验证，该文章是否存在
        Article article = articleMapper.selectByPrimaryKey(articleDTO.getId());
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
        }
        // 验证需要修改的关联数据
        verifyArticle(null, articleDTO.getCategoryId(), articleDTO.getTags());

        // 验证通过，设置修改字段
        Article record = new Article();
        // 设置主键
        record.setId(articleDTO.getId());
        // 设置标题
        record.setTitle(articleDTO.getTitle());
        // 设置内容
        record.setContext(articleDTO.getContext());
        // 设置展示图
        record.setShowImg(articleDTO.getShowImg());
        // 设置分类
        record.setCategoryId(articleDTO.getCategoryId());
        // 设置标签
        record.setTagsId(articleDTO.getTags());
        // 设置修改时间
        record.setGmtModified(System.currentTimeMillis());
        int isSuccess = articleMapper.updateByPrimaryKeySelective(record);
        if (isSuccess != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
        }

        // 判断分类的是否发生修改
        if (!article.getCategoryId().equals(articleDTO.getCategoryId())) {
            // 原有分类的文章数量减一
            int decrArticleCount = categoryExtMapper.decrArticleCount(article.getCategoryId());
            if (decrArticleCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
            }
            // 修改后分类的文章数量加一
            int incrArticleCount = categoryExtMapper.incrArticleCount(articleDTO.getCategoryId());
            if (incrArticleCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
            }
        }

        // 原有标签的文章数量减一
        String[] oldTagsArray = article.getTagsId().split(",");
        for (String tagId : oldTagsArray) {
            int decrArticleCount = tagExtMapper.decrArticleCount(Integer.parseInt(tagId));
            if (decrArticleCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
            }
        }

        // 修改后的文章数量加一
        String[] newTagsArray = articleDTO.getTags().split(",");
        for (String tagId : newTagsArray) {
            int incrArticleCount = tagExtMapper.incrArticleCount(Integer.parseInt(tagId));
            if (incrArticleCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.UPDATE_ARTICLE_ERROR);
            }
        }

        // 添加成功，返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("article", record);
        return resultMap;
    }

    /**
     * 根据id删除文章
     * @param id
     */
    @Transactional
    @Override
    public void deleteArticle(Integer id) {
        // 验证该文章是否存在
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
        }
        // 验证通过，删除文章
        int isSuccess = articleMapper.deleteByPrimaryKey(id);
        if (isSuccess != 1) {
            throw new CustomizeException(CustomizeErrorCode.DELETE_ARTICLE_ERROR);
        }

        // 分类的文章数量减一
        int decrArticleCount = categoryExtMapper.decrArticleCount(article.getCategoryId());
        if (decrArticleCount != 1) {
            throw new CustomizeException(CustomizeErrorCode.DELETE_ARTICLE_ERROR);
        }

        // 标签的文章数量减一
        String[] tagArray = article.getTagsId().split(",");
        for (String tagId : tagArray) {
            int isDecrTagCount = tagExtMapper.decrArticleCount(Integer.parseInt(tagId));
            if (isDecrTagCount != 1) {
                throw new CustomizeException(CustomizeErrorCode.DELETE_ARTICLE_ERROR);
            }
        }

    }

    /**
     * 查询当前访客对当前文章点赞
     * @param visitorId 访客id
     * @param articleId 文章id
     * @return
     */
    @Override
    public Map<String, Object> findArticleLike(Integer visitorId, Integer articleId) {
        VisitorLikeExample visitorLikeExample = new VisitorLikeExample();
        visitorLikeExample.createCriteria()
                .andVisitorIdEqualTo(visitorId)
                .andArticleIdEqualTo(articleId);
        List<VisitorLike> visitorLikes = visitorLikeMapper.selectByExample(visitorLikeExample);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("isLike", visitorLikes.size());
        return resultMap;
    }


    @Autowired
    private InformMapper informMapper;

    /**
     * 文章点赞
     * @param articleLikeDTO
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> giveLike(ArticleLikeDTO articleLikeDTO) {
        // 查询点赞记录表中是否存在该用户已点赞情况
        VisitorLikeExample visitorLikeExample = new VisitorLikeExample();
        visitorLikeExample.createCriteria()
                .andVisitorIdEqualTo(articleLikeDTO.getVisitorId())
                .andArticleIdEqualTo(articleLikeDTO.getArticleId());
        List<VisitorLike> visitorLikes = visitorLikeMapper.selectByExample(visitorLikeExample);
        // 判断点赞类型
        if (articleLikeDTO.getType().equals(1)) {
            // 点赞
            // 判断结果集长度
            if (visitorLikes.size() == 0) {
                // 说明当前访客该文章未点赞,执行点赞操作
                // 增加文章表，当前文章的点赞数量
                Integer isIncrCount = articleExtMapper.incrArticleLikeCount(articleLikeDTO.getArticleId());
                if (isIncrCount != 1) {
                    throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
                }
                // 点赞访客记录表中，添加点赞记录
                VisitorLike visitorLike = new VisitorLike();
                visitorLike.setVisitorId(articleLikeDTO.getVisitorId());
                visitorLike.setArticleId(articleLikeDTO.getArticleId());
                visitorLike.setGmtCreate(System.currentTimeMillis());
                int isInsert = visitorLikeMapper.insertSelective(visitorLike);
                if (isInsert != 1) {
                    throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
                }

                // 添加点赞通知
                Inform inform = new Inform();
                // 设置通知类型
                inform.setType(InformType.INFORM_LIKE.getType());
                // 设置通知人
                inform.setVisitorId(articleLikeDTO.getVisitorId());
                // 设置文章id
                inform.setArticleId(articleLikeDTO.getArticleId());
                // 设置通知时间
                inform.setGmtCreate(System.currentTimeMillis());
                // 执行insert语句
                int isInform = informMapper.insertSelective(inform);
                if (isInform != 1) {
                    throw new CustomizeException(CustomizeErrorCode.ADD_INFORM_ERROR);
                }
            } else {
                throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
            }
        } else {
            // 取消点赞
            // 判断结果集长度
            if (visitorLikes.size() == 1) {
                // 说明当前访客该文章已点赞，执行取消操作
                Integer isDecrCount = articleExtMapper.decrArticleLikeCount(articleLikeDTO.getArticleId());
                if (isDecrCount != 1) {
                    throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
                }
                // 点赞访客记录表中，删除点赞记录
                int isDelete = visitorLikeMapper.deleteByPrimaryKey(visitorLikes.get(0).getId());
                if (isDelete != 1) {
                    throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
                }
            } else {
                throw new CustomizeException(CustomizeErrorCode.VISITOR_LIKE_ERROR);
            }
        }
        return null;
    }

    /**
     * 格式化search
     * 格式为：linux|spring|java
     *
     * @param search
     * @return
     */
    private String toSearch(String search) {
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

    /**
     * 验证文章的关联数据是否合法
     */
    private void verifyArticle(@Nullable Integer adminId, Integer cateId, String tags) {
        // 验证文章发表人是否存在
        if (adminId != null) {
            Admin admin = adminMapper.selectByPrimaryKey(adminId);
            if (admin == null) {
                throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
            }
        }
        // 验证分类是否存在
        Category category = categoryMapper.selectByPrimaryKey(cateId);
        if (category == null) {
            throw new CustomizeException(CustomizeErrorCode.CATEGORY_NOT_FOUND);
        }
        // 验证标签
        String[] tagArray = tags.split(",");
        for (String tagId : tagArray) {
            Tag tag = tagMapper.selectByPrimaryKey(Integer.parseInt(tagId));
            if (tag == null) {
                throw new CustomizeException(CustomizeErrorCode.TAG_NOT_FOUND);
            }
        }
    }
}
