package com.lhk.dao;

import com.lhk.domain.Role;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-21-20:33
 */
public interface RoleDao {
    List<Role> list();

    void save(Role role);

    List<Role> queryRoleByUserId(Long id);
}
