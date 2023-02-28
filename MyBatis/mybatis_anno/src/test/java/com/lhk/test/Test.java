package com.lhk.test;

import com.lhk.mapper.UserMapper;
import com.lhk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-27-16:51
 */
public class Test {

    private UserMapper userMapper;
    @Before
    public void before() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动提交事务
        userMapper=sqlSession.getMapper(UserMapper.class);
    }

    @org.junit.Test
    public void test1(){
        User user = new User();
        user.setName("lhk666");
        user.setPwd("123456");
        userMapper.insertUser(user);
    }

    @org.junit.Test
    public void test2(){
        User user = userMapper.queryUserById(2);
        System.out.println(user);
    }

    @org.junit.Test
    public void test3(){
        userMapper.deleteUserById(1);
    }

    @org.junit.Test
    public void test4(){
        List<User> users = userMapper.queryUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void test5(){
        User user = new User();
        user.setId(2);
        user.setName("TheMutents");
        user.setPwd("1122334455");
        userMapper.update(user);
    }



}
