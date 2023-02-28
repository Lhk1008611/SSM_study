package com.lhk.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author TheMutents
 * @creat on 2021-12-08-23:21
 */
@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime; //属性名与数据库中字段名不一致，设置驼峰命名解决
    private int views;
}
