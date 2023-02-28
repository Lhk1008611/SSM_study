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
public class Test3 {

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
        List<User> users = userMapper.queryUserAndOrder();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void test2(){
        List<User> users = userMapper.queryUserAndRole();
        for (User user : users) {
            System.out.println(user);
        }
    }




}
