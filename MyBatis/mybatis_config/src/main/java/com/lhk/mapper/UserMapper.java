package com.lhk.mapper;

import com.lhk.pojo.User;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-26-23:15
 */
public interface UserMapper {
    void insertUser(User user);

    User queryUserById(int id);

    List<User> queryUser();
}
