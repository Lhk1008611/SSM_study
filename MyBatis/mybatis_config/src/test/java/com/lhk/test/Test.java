package com.lhk.test;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhk.mapper.UserMapper;
import com.lhk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-26-23:22
 */
public class Test {
    @org.junit.Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = builder.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setName("lhk");
        user.setPwd("112345");
        //需要将Date类型装换成数据库中的bigint类型
        //通过自定义类型装换器处理
        user.setBirthday(new Date());

        mapper.insertUser(user);

        sqlSession.commit();
        sqlSession.close();

    }

    @org.junit.Test
    public void test2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = builder.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println("user中的birthday："+user.getBirthday());

        sqlSession.commit();
        sqlSession.close();

    }

    @org.junit.Test
    public void test3() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = builder.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页相关参数,当前页 + 每页显示条数
        PageHelper.startPage(1,2);

        List<User> users = mapper.queryUser();
        for (User user : users) {
            System.out.println(user);
        }

        //获得与分页相关的信息
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println("首页："+userPageInfo.getFirstPage());
        System.out.println("当前页："+userPageInfo.getPageNum());
        System.out.println("总页数："+userPageInfo.getPages());
        System.out.println("总记录条数："+userPageInfo.getTotal());

        sqlSession.commit();
        sqlSession.close();

    }
}
