package com.fish.myspringboot.entity.requestParam;

import lombok.Data;

@Data
public class BlogQueryParam extends PageParam {

    /**
     * 标题
     */
    private String title = "";

    /**
     * 描述
     */
    private String description = "";
}
