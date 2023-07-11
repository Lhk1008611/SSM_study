package com.lhk.service.serviceImpl;

import com.lhk.dao.UserDao;
import com.lhk.dao.daoimpl.UserDaoImpl;
import com.lhk.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author TheMutents
 * @creat on 2021-12-12-18:11
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

//    /**
//     * set方法实现对Bean的依赖注入
//     * @param userDao
//     */
//    public  void setUserDao(UserDao userDao){
//        this.userDao=userDao;
//    }
    @Override
    public void save() {
        userDao.save();
    }

}
