package com.lhk.dao.impl;

import com.lhk.dao.UserDao;
import com.lhk.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-22-13:20
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> queryAllUser() {
        List<User> userList = jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public Long saveUser(final User user) {
//        jdbcTemplate.update("insert into sys_user values (?,?,?,?,?)",null,user.getUsername(),user.getEmail(),user.getPassword(),user.getPhoneNum());

        //1.创建PreparedStatementCreator对象，并实现接口方法
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //里面需要使用原始JDBC完成一个PreparedStatement的组建
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values (?,?,?,?,?)", com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setObject(2,user.getUsername());
                preparedStatement.setObject(3,user.getEmail());
                preparedStatement.setObject(4,user.getPassword());
                preparedStatement.setObject(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
        //2.创建KeyHolder对象，用于存储主键的对象
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        //通过update(PreparedStatementCreator,KeyHolder)方法获得自动增加的userId（主键）
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        //通过KeyHolder对象获得主键
        long userid = generatedKeyHolder.getKey().longValue();


        return userid;
    }

    @Override
    public void saveUserRoleRelation(Long userId, Long[] roleIds) {
        for (Long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)",userId,roleId);
        }
    }

    @Override
    public void deleteUserRoleRelation(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where userId=?",userId);
    }

    @Override
    public void deleteUser(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?",userId);
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) throws EmptyResultDataAccessException {
        //queryForObject()调用后查不到数据会抛出异常EmptyResultDataAccessException
        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
}
