package com.lhk.dao.impl;

import com.lhk.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author TheMutents
 * @creat on 2021-12-15-11:25
 */
//<bean id="userdao" class="com.lhk.dao.impl.UserDaoImpl"></bean>
//使用如下注解
//@Component("userdao")
    @Repository("userdao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running.....");
    }
}
