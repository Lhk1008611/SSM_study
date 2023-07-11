package com.lhk.dao;

import com.lhk.domain.User;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-22-13:20
 */
public interface UserDao {

    List<User> queryAllUser();

    Long saveUser(User user);

    void saveUserRoleRelation(Long id, Long[] roleIds);

    void deleteUserRoleRelation(Long userId);

    void deleteUser(Long userId);

    User queryUserByNameAndPassword(String username, String password);
}
