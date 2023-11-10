package com.fish.myspringboot.entity.requestParam;

import lombok.Data;

@Data
public class BlogQueryParam extends PageParam {
    private String title = "";
    private String description = "";
}
