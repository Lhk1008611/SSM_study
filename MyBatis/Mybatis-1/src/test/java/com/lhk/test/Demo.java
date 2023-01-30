package com.lhk.test;

import com.lhk.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * /**
 *    mybatis常用API：
 *    SqlSessionFactoryBuilder().build(inputStream)
 *    sqlSessionFactory.openSession()  开启一个不会自动提交的事务，进行更新操作时需要手动提交
 *    sqlSessionFactory.openSession(boolean autoCommit)  参数设置是否自动提交
 *    sqlSession.selectList("userMapper.selectUser");
 *    sqlSession.insert("userMapper.insertUser",user1)
 *    sqlSession.update("userMapper.updateUser",user1)
 *    sqlSession.delete("userMapper.deleteUser",14)
 *    sqlSession.commit()  手动提交事务
 *
 * @author TheMutents
 * @creat on 2021-10-13-10:19
 */

    public class Demo {
        @Test
        public void query(){
            SqlSession sqlSession=null;

            try {
                //加载核心配置文件
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                //获得Session工厂对象
                SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
                //获得SqlSession对象
                sqlSession=sqlSessionFactory.openSession();
                //selectList()的参数：namespace+id
                List<User> userList = sqlSession.selectList("userMapper.selectUser");
                for(User user:userList){
                    System.out.println(user);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                sqlSession.close();
            }
        }

    @Test
    public void insert(){

        User user1 = new User();
        user1.setName("mary");
        user1.setPwd("5566123");

        SqlSession sqlSession=null;
        try {
            //加载核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获得Session工厂对象
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //获得SqlSession对象
            sqlSession=sqlSessionFactory.openSession();
            //执行操作  参数：namespace+id
            //mybatis默认事务是不提交的，mybatis执行更新操作时需要提交事务,
            //执行查询操作时不需要提交事务
            sqlSession.insert("userMapper.insertUser",user1);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void update(){

        User user1 = new User();
        user1.setId(13);
        user1.setName("Tom");
        user1.setPwd("556677");

        SqlSession sqlSession=null;
        try {
            //加载核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获得Session工厂对象
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //获得SqlSession对象
            sqlSession=sqlSessionFactory.openSession();
            //执行操作  参数：namespace+id
            //mybatis默认事务是不提交的，mybatis执行更新操作时需要提交事务,
            //执行查询操作时不需要提交事务
            sqlSession.update("userMapper.updateUser",user1);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void delete(){

        SqlSession sqlSession=null;
        try {
            //加载核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获得Session工厂对象
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //获得SqlSession对象
            sqlSession=sqlSessionFactory.openSession();
            //执行操作  参数：namespace+id
            //mybatis默认事务是不提交的，mybatis执行更新操作时需要提交事务,
            //执行查询操作时不需要提交事务
            sqlSession.delete("userMapper.deleteUser",14);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }


    }

