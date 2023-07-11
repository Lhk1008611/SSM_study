package com.lhk.test;

import com.lhk.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author TheMutents
 * @creat on 2021-12-12-16:37
 */
public class UserDaoTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();

    }
}
