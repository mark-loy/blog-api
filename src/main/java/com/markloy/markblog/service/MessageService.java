package com.markloy.markblog.service;

import com.markloy.markblog.dto.MessageDTO;

import java.util.List;
import java.util.Map;

public interface MessageService {
    Map<String, Object> saveMessage(MessageDTO messageDTO);

    List<Map<String, Object>> findAllMessage(Integer currentPage, Integer offset, Integer type);

    long countMessage(Integer type);

    Map<String, Object> updateMessageState(Integer id, Boolean state);
}
