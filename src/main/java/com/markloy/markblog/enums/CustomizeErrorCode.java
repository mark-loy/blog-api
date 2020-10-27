package com.markloy.markblog.enums;

public enum CustomizeErrorCode {
    USER_NOT_FOUND(400, "用户不存在"),
    PASSWORD_ERROR(400, "密码错误"),
    PAGE_ERROR(400, "页码错误"),
    UPDATE_USER_ERROR(402, "更新访客信息失败"),
    ADD_USER_ERROR(401, "保存访客信息失败"),
    ADD_MESSAGE_ERROR(401, "保存留言信息失败");

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
