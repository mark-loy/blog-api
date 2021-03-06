package com.markloy.markblog.service.impl;

import com.markloy.markblog.dto.MessageDTO;
import com.markloy.markblog.enums.CustomizeErrorCode;
import com.markloy.markblog.enums.InformType;
import com.markloy.markblog.exception.CustomizeException;
import com.markloy.markblog.mapper.InformMapper;
import com.markloy.markblog.mapper.MessageMapper;
import com.markloy.markblog.mapper.VisitorMapper;
import com.markloy.markblog.pojo.Inform;
import com.markloy.markblog.pojo.Message;
import com.markloy.markblog.pojo.MessageExample;
import com.markloy.markblog.pojo.Visitor;
import com.markloy.markblog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private InformMapper informMapper;

    @Autowired
    private InformServiceImpl informService;

    /**
     * 保存留言
     *
     * @param messageDTO
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> saveMessage(MessageDTO messageDTO) {
        // 1. 保存留言数据
        Message message = new Message();
        // 设置留言类型
        message.setType(messageDTO.getType());
        // 设置父级id
        message.setParentId(messageDTO.getParentId());
        // 设置访客id
        message.setVisitorId(messageDTO.getVisitorId());
        // 设置内容
        message.setContent(messageDTO.getContent());
        // 设置留言状态
        message.setState(false);
        // 设置留言时间
        Date date = new Date();
        message.setGmtCreate(date.getTime());
        // 执行insert语句
        int isInsert = messageMapper.insertSelective(message);
        if (isInsert != 1) throw new CustomizeException(CustomizeErrorCode.ADD_MESSAGE_ERROR);

        // 2. 添加通知
        Inform inform = new Inform();
        // 设置通知类型
        inform.setType(InformType.INFORM_MESSAGE.getType());
        // 设置通知人
        inform.setVisitorId(messageDTO.getVisitorId());
        // 设置留言id
        inform.setMessageId(message.getId());
        // 设置通知时间
        inform.setGmtCreate(System.currentTimeMillis());
        // 执行insert语句
        int isInform = informMapper.insertSelective(inform);
        if (isInform != 1) {
            throw new CustomizeException(CustomizeErrorCode.ADD_INFORM_ERROR);
        }
        // 结果返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return resultMap;
    }

    /**
     * 查询全部留言
     *
     * @return
     */
    @Override
    @Transactional
    public List<Map<String, Object>> findAllMessage(Integer currentPage, Integer offset, Integer type) {

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
                totalPage = totalCount / offset;
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
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andTypeEqualTo(1);
        if (type == 1) {
            criteria.andStateEqualTo(true);
        }
        // 执行select语句
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
            messageInfo.put("state", message.getState());
            messageInfo.put("parentId", message.getParentId());

            // 用一级留言id，查询二级留言
            MessageExample childMessageExample = new MessageExample();
            childMessageExample.setOrderByClause("gmt_create");
            MessageExample.Criteria childrenCriteria = childMessageExample.createCriteria();
            childrenCriteria.andParentIdEqualTo(message.getId());
            if (type == 1) {
                childrenCriteria.andStateEqualTo(true);
            }
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
                childMessageInfo.put("state", childMessage.getState());
                childMessageInfo.put("parentId", childMessage.getParentId());

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
     *
     * @return
     */
    @Override
    public long countMessage(Integer type) {

        MessageExample example = new MessageExample();
        if (type == 1) {
            example.createCriteria().andStateEqualTo(true);
        }
        return messageMapper.countByExample(example);
    }

    /**
     * 修改留言状态
     *
     * @param id    留言id
     * @param state
     * @return 修改的留言记录
     */
    @Override
    public Map<String, Object> updateMessageState(Integer id, Boolean state) {
        // 根据id查询留言
        Message message = messageMapper.selectByPrimaryKey(id);
        // 判断留言是否存在
        if (message == null) {
            throw new CustomizeException(CustomizeErrorCode.MESSAGE_NOT_FOUND);
        }
        // 修改留言状态
        Message record = new Message();
        // 设置主键
        record.setId(id);
        // 设置留言状态
        record.setState(state);
        // 执行update
        int isUpdate = messageMapper.updateByPrimaryKeySelective(record);
        // 判断是否更新成功
        if (isUpdate != 1) {
            throw new CustomizeException(CustomizeErrorCode.UPDATE_MESSAGE_STATE_ERROR);
        }

        // 修改当前留言的通知状态
        informService.updateInformState(id);

        // 结果封装
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", record);
        return resultMap;
    }
}
