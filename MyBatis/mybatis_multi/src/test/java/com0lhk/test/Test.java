package com0lhk.test;

import com.lhk.mapper.OrderMapper;
import com.lhk.mapper.UserMapper;
import com.lhk.pojo.Order;
import com.lhk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-27-13:38
 */
public class Test {


    /**
     * 测试一对多  一个order对应一个user
     * @throws IOException
     */
    @org.junit.Test
    public void test1() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> orders = mapper.queryOrder();

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @org.junit.Test
    public void test2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.queryUser();
        for (User user : users) {
            System.out.println(user);
        }

    }
}
