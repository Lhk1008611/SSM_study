package com.lhk.factory;

import com.lhk.dao.UserDao;
import com.lhk.dao.daoimpl.UserDaoImpl;

/**
 * @author TheMutents
 * @creat on 2021-12-12-17:58
 */
public class DynamicFactory {
    public  UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
