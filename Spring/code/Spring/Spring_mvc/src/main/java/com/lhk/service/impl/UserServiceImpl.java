package com.lhk.service.impl;

import com.lhk.dao.UserDao;
import com.lhk.service.UserService;

/**
 * @author TheMutents
 * @creat on 2021-12-15-19:23
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
