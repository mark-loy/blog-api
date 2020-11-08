package com.markloy.markblog.service.impl;

import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.*;
import com.markloy.markblog.pojo.*;
import com.markloy.markblog.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformServiceImpl implements InformService {

    @Autowired
    private InformMapper informMapper;

    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询未读通知
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> findInformByState() {
        InformExample informExample = new InformExample();
        // 设置排序字段
        informExample.setOrderByClause("gmt_create desc");
        // 设置未读条件
        informExample.createCriteria()
                .andStateEqualTo(0);
        // 执行select语句
        List<Inform> informs = informMapper.selectByExample(informExample);
        List<Map<String, Object>> maps = new ArrayList<>();
        if (informs.size() > 0) {
            // 遍历
            for (Inform inform : informs) {
                // 查询通知人信息
                Visitor visitor = visitorMapper.selectByPrimaryKey(inform.getVisitorId());
                if (visitor == null) {
                    throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
                }
                Map<String, Object> visitorMap = new HashMap<>();
                visitorMap.put("id", visitor.getId());
                visitorMap.put("visitor_name", visitor.getVisitorName());
                // 查询被通知人信息
                Admin admin = adminMapper.selectByPrimaryKey(inform.getAdminId());
                if (admin == null) {
                    throw new CustomizeException(CustomizeErrorCode.USER_NOT_FOUND);
                }
                Map<String, Object> adminMap = new HashMap<>();
                adminMap.put("id", admin.getId());
                adminMap.put("pet_name", admin.getPetName());
                // 查询该通知是否是点赞通知
                Map<String, Object> articleMap = new HashMap<>();
                if (inform.getArticleId() != 0) {
                    Article article = articleMapper.selectByPrimaryKey(inform.getArticleId());
                    if (article == null) {
                        throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
                    }
                    articleMap.put("id", article.getId());
                    articleMap.put("title", article.getTitle());
                }

                // 查询该通知是否是留言通知
                Map<String, Object> messageMap = new HashMap<>();
                if (inform.getMessageId() != 0) {
                    Message message = messageMapper.selectByPrimaryKey(inform.getMessageId());
                    if (message == null) {
                        throw new CustomizeException(CustomizeErrorCode.MESSAGE_NOT_FOUND);
                    }
                    messageMap.put("id", message.getId());
                    messageMap.put("content", message.getContent());
                }

                // 通知信息封装
                Map<String, Object> informMap = new HashMap<>();
                informMap.put("visitor", visitorMap);
                informMap.put("admin", adminMap);
                informMap.put("article", articleMap);
                informMap.put("message", messageMap);
                informMap.put("id", inform.getId());
                informMap.put("state", inform.getState());
                informMap.put("type", inform.getType());
                informMap.put("gmt_create", inform.getGmtCreate());
                maps.add(informMap);

            }
        }
        // 结果返回
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("informs", maps);
        return resultMap;
    }

    /**
     * 修改通知状态
     * @param informId 通知id
     */
    public void updateInformState(Integer informId) {
        // 判断是否需要修改通知状态
        if (informId != 0) {
            Inform inform = new Inform();
            // 设置主键
            inform.setId(informId);
            // 设置状态
            inform.setState(1);
            // 执行update语句
            int isUpdate = informMapper.updateByPrimaryKeySelective(inform);
            if (isUpdate != 1) {
                throw new CustomizeException(CustomizeErrorCode.UPDATE_INFORM_STATE_ERROR);
            }
        }
    }
}
