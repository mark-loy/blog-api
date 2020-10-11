package com.markloy.markblog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {

    private int code;
    private String message;
    private Object data;

    public static ResultDTO  success(Object data) {
        return success(200, "操作成功", data);
    }

    public static ResultDTO  success(int code, String message, Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static ResultDTO fail(String message) {
        return fail(400, message, null);
    }

    public static ResultDTO fail(int code, String message, Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

}
