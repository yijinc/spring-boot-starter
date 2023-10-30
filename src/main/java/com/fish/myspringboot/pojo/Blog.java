package com.fish.myspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private BigInteger id;
    private String title;
    private String description;
    private String content;
    private BigInteger userId;
    private Timestamp createTime;
    private Timestamp updateTime;
}