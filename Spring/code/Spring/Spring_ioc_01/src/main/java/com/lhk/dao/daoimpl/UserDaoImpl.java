package com.lhk.dao.daoimpl;

import com.lhk.dao.UserDao;
import com.lhk.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author TheMutents
 * @creat on 2021-12-12-16:31
 */
public class UserDaoImpl implements UserDao {
    private String userName;
    private int age;
    private List<User> UserList;
    private Map<String, User> userMap;
    private Properties properties;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserList(List<User> userList) {
        UserList = userList;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

     public UserDaoImpl() {
        System.out.println("UserDaoImpl对象被创建.....");
    }

    public void init(){
        System.out.println("UserDaoImpl的初始化方法.....");
    }

    public void destroy(){
        System.out.println("UserDaoImpl的销毁方法.....");
    }

    @Override
    public void save() {
        System.out.println(userName+"========"+age);
        System.out.println(UserList);
        System.out.println(userMap);
        System.out.println(properties);
        System.out.println("save user");
    }
}
