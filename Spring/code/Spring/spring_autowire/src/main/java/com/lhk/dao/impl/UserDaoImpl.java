package com.lhk.dao.impl;

import com.lhk.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.printf("自动装配");
    }
}
