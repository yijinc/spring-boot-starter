package org.example.domain.param;

import jakarta.validation.constraints.NotBlank;
import lombok. Data;

@Data
public class BlogBody {

    /**
     * 标题
     */
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 描述
     */
    @NotBlank(message = "请输入描述")
    private String description;

    /**
     * 内容
     */
    @NotBlank(message = "请输入内容")
    private String content;

}
