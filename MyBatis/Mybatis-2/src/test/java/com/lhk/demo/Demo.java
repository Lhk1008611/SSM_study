package com.lhk.demo;

import com.lhk.bean.User;
import com.lhk.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-10-13-11:00
 */
public class Demo {
    @Test
    public void query(){
        SqlSession sqlSession=null;

        try {

            /*
             * 通过工厂模式获取SQL的会话(SqlSession)对象
             * SqlSession : 是mybatis提供的操作数据库的对象
             */
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //sqlSession=sqlSessionFactory.openSession(true); 设置事务自动提交
            sqlSession=sqlSessionFactory.openSession();

            //基于UserMapper接口编程
            /*
                通过代理模式获取到UserMapper的代理实现对象
                getMapper() : 底层实现了UserMapper接口，并返回实现类的对象
             */
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

            for(User user:userList){
                System.out.println(user);
            }

            /*
             * 为设置自动提交事务时，需要手动提交事务才能插入成功
             */
            sqlSession.commit();

            //关闭会话
            sqlSession.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetUserById() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = builder.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(13);
        System.out.println(user);

        sqlSession.close();
    }
}
