package com.lhk.service.impl;

import com.lhk.dao.RoleDao;
import com.lhk.domain.Role;
import com.lhk.service.RoleService;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-21-20:30
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.list();
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
