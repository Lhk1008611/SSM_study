package com.lhk.factory;

import com.lhk.dao.UserDao;
import com.lhk.dao.daoimpl.UserDaoImpl;

/**
 * @author TheMutents
 * @creat on 2021-12-12-17:34
 */
public class StaticFactory {

    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
