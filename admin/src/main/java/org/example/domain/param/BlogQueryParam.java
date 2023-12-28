package org.example.domain.param;

import lombok.Data;
import org.example.domain.PageParam;

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
