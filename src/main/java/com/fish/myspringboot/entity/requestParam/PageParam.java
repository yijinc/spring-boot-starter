package com.fish.myspringboot.entity.requestParam;

import lombok.Data;

@Data
public class PageParam {
    private Long current = 1L;
    private Long pageSize = 10L;
}
