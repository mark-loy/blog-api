package com.markloy.markblog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageDTO {

    /**
     * 当前留言父级留言id
     */
    @NotNull(message = "留言父级不能为空")
    private Integer parentId;
    /**
     * 留言类型
     * 1.一级留言
     * 2.二级留言
     */
    @NotNull(message = "留言类型不能为空")
    private Integer type;
    /**
     * 留言人id
     */
    @NotNull(message = "留言人不能为空")
    private Integer visitorId;
    /**
     * 留言内容
     */
    @NotBlank(message = "留言内容不能为空")
    private String content;

}
