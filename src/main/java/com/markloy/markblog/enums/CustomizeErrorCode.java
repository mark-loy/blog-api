package com.markloy.markblog.enums;

public enum CustomizeErrorCode {
    USER_NOT_FOUND(400, "用户不存在"),
    PASSWORD_ERROR(400, "密码错误"),
    PAGE_ERROR(400, "页码错误");

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
