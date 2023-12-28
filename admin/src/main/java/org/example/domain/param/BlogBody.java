package org.example.domain.param;


import lombok. Data;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

@Data
public class BlogBody {

    /**
     * 标题
     */
//    @NotNull(message = "请输入标题")
    private String title;

    /**
     * 描述
     */
//    @NotBlank(message = "请输入描述")
    private String description;

    /**
     * 内容
     */
//    @NotEmpty(message = "请输入内容")
    private String content;

}
