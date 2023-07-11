package com.lhk.service;

import com.lhk.domain.User;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-22-13:16
 */
public interface UserService {
    List<User> list();

    void save(User user, Long[] roleIds);

    void deleteUserById(Long userId);

    User queryUserByNameAndPassword(String username, String password);
}
