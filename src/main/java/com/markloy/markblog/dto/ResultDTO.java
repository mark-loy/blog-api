package com.markloy.markblog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ResultDTO implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回的数据
     */
    private Map<String, Object> data;

    public static ResultDTO  success(Map<String, Object> data) {
        return success(200, "操作成功", data);
    }

    public static ResultDTO  success(int code, String message, Map<String, Object> data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static ResultDTO fail(String message) {
        return fail(400, message, null);
    }

    public static ResultDTO fail(int code, String message, Map<String, Object> data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

}
