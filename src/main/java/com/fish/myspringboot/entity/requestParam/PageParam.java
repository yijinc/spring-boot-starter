package com.fish.myspringboot.entity.requestParam;

import lombok.Data;

@Data
public class PageParam {
    /**
     * 当前页
     */
    private Long current = 1L;

    /**
     * 页面大小
     */
    private Long pageSize = 10L;
}
