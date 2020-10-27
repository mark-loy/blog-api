package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.MessageDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.MessageMapper;
import com.markloy.markblog.mapper.VisitorMapper;
import com.markloy.markblog.pojo.Message;
import com.markloy.markblog.pojo.MessageExample;
import com.markloy.markblog.pojo.Visitor;
import com.markloy.markblog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 保存留言
     * @param messageDTO
     * @return
     */
    @Override
    public Map<String, Object> saveMessage(MessageDTO messageDTO) {
        // 设置保存数据
        Message message = new Message();
        message.setType(messageDTO.getType());
        message.setParentId(messageDTO.getParentId());
        message.setVisitorId(messageDTO.getVisitorId());
        message.setContent(messageDTO.getContent());
        message.setGmtCreate(System.currentTimeMillis());
        // 执行insert语句
        int isInsert = messageMapper.insertSelective(message);
        if (isInsert != 1) throw new CustomizeException(CustomizeErrorCode.ADD_MESSAGE_ERROR);
        // 获取插入留言的id
        Integer id = message.getId();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message_id", id);
        return resultMap;
    }

    @Autowired
    private VisitorMapper visitorMapper;

    /**
     * 查询全部留言
     * @return
     */
    @Override
    public List<Map<String, Object>> findAllMessage(Integer currentPage, Integer offset) {
        // 判断当前页是否小于1
        if (currentPage < 1) {
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        }

        // 查询总留言数
        long totalCount = messageMapper.countByExample(new MessageExample());

        // 计算总页数
        long totalPage = 1;
        if (totalCount > 0) {
            //判断文章总页数
            if (totalCount % offset == 0) {
                totalPage =  totalCount / offset;
            } else {
                totalPage = totalCount / offset + 1;
            }
        }

        // 判断当前页和总页数
        if (currentPage > totalPage) {
            throw new CustomizeException(CustomizeErrorCode.PAGE_ERROR);
        }

        // 计算分页查询起始下标
        int startIndex = (currentPage - 1) * offset;

        // 设置查询条件
        MessageExample messageExample = new MessageExample();
        // 按照创建时间排序
        messageExample.setOrderByClause("gmt_create desc");
        // 查询一级留言
        messageExample.createCriteria()
                .andTypeEqualTo(1);
        // 执行insert语句
        List<Message> messages = messageMapper.selectByExampleWithRowbounds(messageExample, new RowBounds(startIndex, offset));

        //二级留言集合存储
        List<Map<String, Object>> messageList = new ArrayList<>();

        // 遍历一级留言
        messages.forEach(message -> {
            // 一级留言人信息存储
            Map<String, Object> visitorInfo = new HashMap<>();
            // 查询一级留言人信息
            Visitor visitor = visitorMapper.selectByPrimaryKey(message.getVisitorId());
            visitorInfo.put("id", visitor.getId());
            visitorInfo.put("avatar_url", visitor.getAvatarUrl());
            visitorInfo.put("visitor_name", visitor.getVisitorName());
            visitorInfo.put("source", visitor.getSource());
            // 一级留言内容存储
            Map<String, Object> messageInfo = new HashMap<>();
            messageInfo.put("id", message.getId());
            messageInfo.put("gmt_create", message.getGmtCreate());
            messageInfo.put("content", message.getContent());

            // 用一级留言id，查询二级留言
            MessageExample childMessageExample = new MessageExample();
            childMessageExample.setOrderByClause("gmt_create");
            childMessageExample.createCriteria()
                    .andParentIdEqualTo(message.getId());
            List<Message> childMessages = messageMapper.selectByExample(childMessageExample);

            //一级留言集合存储
            List<Map<String, Object>> childMessageList = new ArrayList<>();

            // 遍历二级留言
            childMessages.forEach(childMessage -> {
                // 二级留言人信息存储
                Map<String, Object> childVisitorInfo = new HashMap<>();
                // 查询二级留言人信息
                Visitor childVisitor = visitorMapper.selectByPrimaryKey(childMessage.getVisitorId());
                childVisitorInfo.put("id", childVisitor.getId());
                childVisitorInfo.put("avatar_url", childVisitor.getAvatarUrl());
                childVisitorInfo.put("visitor_name", childVisitor.getVisitorName());
                childVisitorInfo.put("source", childVisitor.getSource());
                // 二级留言内容存储
                Map<String, Object> childMessageInfo = new HashMap<>();
                childMessageInfo.put("id", childMessage.getId());
                childMessageInfo.put("gmt_create", childMessage.getGmtCreate());
                childMessageInfo.put("content", childMessage.getContent());

                //二级留言对象存储
                Map<String, Object> childMessageObj = new HashMap<>();
                childMessageObj.put("visitor", childVisitorInfo);
                childMessageObj.put("message", childMessageInfo);

                childMessageList.add(childMessageObj);
            });

            // 一级留言对象存储
            Map<String, Object> messageObj = new HashMap<>();
            messageObj.put("visitor", visitorInfo);
            messageObj.put("message", messageInfo);
            messageObj.put("child_messages", childMessageList);

            messageList.add(messageObj);
        });

        return messageList;
    }

    /**
     * 查询留言总数
     * @return
     */
    @Override
    public long countMessage() {

        MessageExample example = new MessageExample();
        long count = messageMapper.countByExample(example);

        return count;
    }
}
