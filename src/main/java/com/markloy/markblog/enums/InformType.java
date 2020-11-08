package com.markloy.markblog.enums;

public enum InformType {
    INFORM_MESSAGE(1, "留言"),
    INFORM_LIKE(2, "点赞");

    private final Integer type;
    private final String description;

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    InformType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
