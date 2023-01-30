package com.lhk.mapper;

import com.lhk.bean.User;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-10-13-10:55
 */
public interface UserMapper {
    //查询所有用户
    public List<User> getUserList();

    public User getUserById(int id);
}
