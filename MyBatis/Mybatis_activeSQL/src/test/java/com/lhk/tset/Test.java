package com.lhk.tset;

import com.lhk.dao.BlogMapper;
import com.lhk.pojo.Blog;
import com.lhk.utils.IDUtils;
import org.apache.ibatis.session.SqlSession;
import com.lhk.utils.MybatisUtils;

import java.util.*;

/**
 * @author TheMutents
 * @creat on 2021-12-09-0:28
 */
public class Test {
    @org.junit.Test
    public void addBlogTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setTitle("Mybatis");
        blog.setAuthor("狂神说");
        blog.setCreateTime(new Date());
        blog.setViews(9999);

        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("Java如此简单");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("Spring如此简单");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("微服务如此简单");
        mapper.addBlog(blog);

        sqlSession.close();
    }

    @org.junit.Test
    public void queryBlogIf(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map=new HashMap();
        map.put("title","Java如此简单");
        map.put("author","狂神说");
        List<Blog> blogs = mapper.queryBlogIf(map);

        for (Blog blog:
             blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @org.junit.Test
    public void queryBlogChoose(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map=new HashMap();
//        map.put("title","Mybatis");
        map.put("author","狂神说");
        map.put("views",99);
        List<Blog> blogs = mapper.queryBlogChoose(map);

        for (Blog blog:
                blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @org.junit.Test
    public void queryBlogForeach(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        List<String> authors = new ArrayList<>();
        authors.add("'狂神说'");
        authors.add("'TheMutents'");

        List<Blog> blogs = mapper.queryBlogForeach(authors);

        for (Blog blog:
                blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @org.junit.Test
    public void updateBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map=new HashMap();
        map.put("title","手撕Mybatis");
//        map.put("author","Lhk1008611");
        map.put("id","f584abce93c74ec595f9f37058a94565");
        int blogs = mapper.updateBlog(map);
        System.out.println(blogs);
        sqlSession.close();
    }
}
