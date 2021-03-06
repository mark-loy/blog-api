package com.markloy.markblog.enums;

public enum CustomizeErrorCode {
    USER_NOT_FOUND(400, "用户不存在"),
    ACCOUNT_ERROR(400, "账号异常"),
    PASSWORD_ERROR(400, "密码错误"),
    PAGE_ERROR(400, "页码错误"),
    UPDATE_USER_ERROR(402, "更新访客信息失败"),
    ADD_USER_ERROR(401, "保存访客信息失败"),
    MESSAGE_NOT_FOUND(400, "留言不存在"),
    ADD_MESSAGE_ERROR(401, "保存留言信息失败"),
    ADD_ARTICLE_ERROR(401, "添加文章信息失败"),
    UPDATE_ARTICLE_ERROR(402, "更新文章信息失败"),
    ARTICLE_NOT_FOUND(400, "文章不存在"),
    CATEGORY_NOT_FOUND(400, "分类不存在"),
    TAG_NOT_FOUND(400,"标签不存在"),
    DELETE_ARTICLE_ERROR(403, "删除文章失败"),
    ADD_CATEGORY_ERROR(401, "保存分类信息失败"),
    UPDATE_CATEGORY_ERROR(402, "更新分类失败"),
    DELETE_CATEGORY_ERROR(403, "删除分类失败"),
    ADD_TAG_ERROR(401, "保存标签失败"),
    UPDATE_TAG_ERROR(402, "修改标签失败"),
    DELETE_TAG_ERROR(403, "删除标签失败"),
    VISITOR_LIKE_ERROR(400, "访客点赞出现错误"),
    VISITOR_NOT_FOUND(400, "访客不存在"),
    INCR_VIEWCOUNT_ERROR(400, "增加浏览数失败"),
    FILE_UPLOAD_ERROR(400, "文件上传失败"),
    UPDATE_ADMIN_ERROR(402, "更新管理员信息失败"),
    ADD_INFORM_ERROR(401, "添加通知信息失败"),
    UPDATE_INFORM_STATE_ERROR(402, "更新通知状态失败"),
    UPDATE_MESSAGE_STATE_ERROR(402, "更新留言状态失败");

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
