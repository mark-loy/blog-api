package com.markloy.markblog.controller;

import com.markloy.markblog.dto.MessageDTO;
import com.markloy.markblog.dto.ResultDTO;
import com.markloy.markblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 保存留言api
     * @param messageDTO
     * @return
     */
    @PostMapping("/save/message")
    public ResultDTO saveMessage(@Validated @RequestBody MessageDTO messageDTO) {

        Map<String, Object> map =  messageService.saveMessage(messageDTO);

        return ResultDTO.success(map);
    }

    /**
     * 查询全部留言api
     * @return
     */
    @GetMapping("/find/message")
    public ResultDTO getMessage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                @RequestParam(value = "offset",defaultValue = "5") Integer offset) {
        // 查询留言信息
        List<Map<String, Object>> maps = messageService.findAllMessage(currentPage, offset);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("messages", maps);
        // 查询留言总数
        long total = messageService.countMessage();
        resultMap.put("total", total);
        return ResultDTO.success(resultMap);
    }
}
