package com.lhk.dao;

import com.lhk.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author TheMutents
 * @creat on 2021-12-08-23:56
 */
public interface BlogMapper {

    //插入数据
    public int addBlog(Blog blog);

    //查询全部博客
    List<Blog> queryBlogIf(Map map);

    //查询博客
    List<Blog> queryBlogChoose(Map map);

    List<Blog> queryBlogForeach(List<String> ids);
    //更新博客
    int updateBlog(Map map);
}
