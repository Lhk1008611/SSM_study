package com.lhk.test;

import com.lhk.dao.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Target;

/**
 * @author TheMutents
 * @creat on 2021-12-12-17:00
 */
public class SpringTest {

    /**
     * 测试scope属性
     * scope="prototype" :多例的
     * scope="singleton" :单例的（默认）
     */
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
//        UserDao userDao2 = (UserDao) applicationContext.getBean("userDao");
        System.out.println(userDao);
        //applicationContext.close(); //手动关闭容器
    }
}
