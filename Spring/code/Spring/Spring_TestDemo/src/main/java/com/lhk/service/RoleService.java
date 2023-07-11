package com.lhk.service;

import com.lhk.domain.Role;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-21-20:29
 */
public interface RoleService {
    List<Role> list();

    void save(Role role);
}
