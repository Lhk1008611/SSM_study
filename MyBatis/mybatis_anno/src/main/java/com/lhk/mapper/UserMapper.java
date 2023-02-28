package com.lhk.mapper;

import com.lhk.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 使用注解实现crud
 * @author TheMutents
 * @creat on 2021-12-26-23:15
 */
public interface UserMapper {

    @Insert(" insert into user2 values(#{id},#{name},#{pwd},#{birthday})")
    void insertUser(User user);

    @Select("select * from user2 where id=#{id}")
    User queryUserById(int id);

    @Select("select *from user2")
    List<User> queryUser();

    @Delete("delete from user2 where id=#{id}")
    void deleteUserById(int id);

    @Update("update user2 set name=#{name},pwd=#{pwd} where id=#{id}")
    void update(User user);

    @Select("select * from user1 where id=#{id}")
    User queryUserById1(int id);

    @Select("select * from user1")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "pwd",property = "pwd"),
            @Result(
                    column = "id",property = "orderList",
                    javaType = List.class,
                    many = @Many(select = "com.lhk.mapper.OrderMapper.queryOrderByUid")
            )
    })
    List<User> queryUserAndOrder();


    @Select("select * from sys_user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username",property = "name"),
            @Result(column = "password",property = "pwd"),
            @Result(column = "id",
                    property = "roleList",
                    javaType = List.class,
                    many = @Many(select = "com.lhk.mapper.RoleMapper.queryRoleByUserId")
            )
    })
    List<User> queryUserAndRole();
}
