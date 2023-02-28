package com.lhk.test;

import com.lhk.mapper.OrderMapper;
import com.lhk.pojo.Order;
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
public class Test2 {

    private OrderMapper orderMapper;
    @Before
    public void before() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动提交事务
        orderMapper=sqlSession.getMapper(OrderMapper.class);
    }

    @org.junit.Test
    public void test1(){
        List<Order> orders = orderMapper.queryOrder();
        for (Order order : orders) {
            System.out.println(order);
        }
    }



}
