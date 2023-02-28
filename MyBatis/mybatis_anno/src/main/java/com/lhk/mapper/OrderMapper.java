package com.lhk.mapper;

import com.lhk.pojo.Order;
import com.lhk.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 使用注解实现一对一查询 （一个order对应一个user）
 * @author TheMutents
 * @creat on 2021-12-27-11:40
 */
public interface OrderMapper {

    @Select("select * from orders where uid=#{uid}")
    List<Order> queryOrderByUid(int uid);

    //多白一对一查询方式二
    @Select("SELECT * FROM orders")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ordertime",property = "orderTime"),
            @Result(column = "total",property = "total"),
            @Result(
                    property = "user",
                    column = "uid",
                    javaType = User.class,
                    one = @One(select = "com.lhk.mapper.UserMapper.queryUserById1")
            )
    })
    List<Order> queryOrder();
    //多白一对一查询方式一
   /* @Select("SELECT *,o.`id` oid FROM user1 u,orders o WHERE o.`uid`=u.`id`")
    @Results({
            @Result(column = "oid",property = "id"),
            @Result(column = "ordertime",property = "orderTime"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "user.id"),
            @Result(column = "name",property = "user.name"),
            @Result(column = "pwd",property = "user.pwd")
    })
    List<Order> queryOrder();*/
}
