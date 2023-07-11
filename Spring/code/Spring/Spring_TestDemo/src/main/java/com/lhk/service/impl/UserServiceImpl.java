package com.lhk.service.impl;

import com.lhk.dao.RoleDao;
import com.lhk.dao.UserDao;
import com.lhk.domain.Role;
import com.lhk.domain.User;
import com.lhk.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-22-13:17
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

//    private JdbcTemplate jdbcTemplate;
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    //记得在applicationCntext.xml的配置UserService中加上roleDao的映射
    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.queryAllUser();
        //给每一个user对象的roles属性传入角色列表
        for (User user : userList) {
            Long id = user.getId();
            List<Role> roles = roleDao.queryRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //向sys_user表中插入数据
        Long userId = userDao.saveUser(user);
        //向user 与 role的关系表中插入数据
        userDao.saveUserRoleRelation(userId,roleIds);
    }

    @Override
    public void deleteUserById(Long userId) {
        //1.先删除在User与Role的关系表中的字段
        userDao.deleteUserRoleRelation(userId);
        //2.再删除sys_User表中的的字段
        userDao.deleteUser(userId);
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        //在业务层捕获异常
        try{
            User user = userDao.queryUserByNameAndPassword(username, password);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
