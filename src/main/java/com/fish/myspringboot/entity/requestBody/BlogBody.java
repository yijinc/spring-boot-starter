package com. fish. myspringboot. entity. requestBody;


import lombok. Data;

import javax. validation. constraints. NotEmpty;

@Data
public class BlogBody {

    /**
     * 标题
     */
    @NotEmpty(message = "请输入标题")
    private String title;

    /**
     * 描述
     */
    @NotEmpty(message = "请输入描述")
    private String description;

    /**
     * 内容
     */
    @NotEmpty(message = "请输入内容")
    private String content;

}
