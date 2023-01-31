package com.lhk.demo;

import com.lhk.bean.User;
import com.lhk.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TheMutents
 * @creat on 2021-10-13-11:00
 */
public class Demo {
    @Test
    public void query(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            //基于UserMapper接口编程
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user1 = userMapper.getUserById(3);

            System.out.println(user1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query1(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setName("小红");
            user.setPassword("123");
            User user1 = mapper.getUserByNameAndPwd(user);
            System.out.println(user1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query2(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setName("小红");
            user.setPassword("123");
            User user1 = mapper.getUserByNameAndPwd1(user);
            System.out.println(user1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query3(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String,String> map=new HashMap<String,String>();
            map.put("id","13");
            map.put("name","花花");
            User user=mapper.getUserByIdAndName(map);
            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setId(8);
            user.setName("寂静岭");
            user.setPassword("123456");
            int i = mapper.updateUser(user);
            sqlSession.commit();
            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insert(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user=new User();
            user.setName("lhk");
            user.setPassword("111555");
            int i = mapper.addUser(user);
            sqlSession.commit();
            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        SqlSession sqlSession=null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            int i = mapper.deleteUser(14);
            sqlSession.commit();
            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
