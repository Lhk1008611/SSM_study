package com.lhk.dao.impl;

import com.lhk.dao.RoleDao;
import com.lhk.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-21-20:34
 */
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public void save(Role role) {
        jdbcTemplate.update("insert into sys_role values(?,?,?)",null,role.getRoleName(),role.getRoleDesc());
    }

    @Override
    public List<Role> queryRoleByUserId(Long id) {
        List<Role> roles = jdbcTemplate.query("select * from sys_role sr,sys_user_role sur where sr.id=sur.roleId and sur.userId=?", new BeanPropertyRowMapper<Role>(Role.class), id);
        return roles;
    }
}
