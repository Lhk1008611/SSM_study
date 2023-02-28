package com.lhk.mapper;

import com.lhk.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author TheMutents
 * @creat on 2021-10-13-16:27
 */
public interface UserMapper {
    //根据用户的id查询用户信息
    //参数一旦加了@Param注解，映射文件的占位符一定与注解里面的名称一致
    public User getUserById(@Param("userId") int Id);

    //根据密码和用户名查询用户
    //参数使用类类型，映射文件的占位符必须是实体类的属性名
    public User getUserByNameAndPwd(User user);
    //扩展,加入@Param注解
    public User getUserByNameAndPwd1(@Param("USER") User user);

    //根据id和用户名查询用户
    //方法参数使用Map集合入参，映射文件中的占位符必须和Map集合中的key一致
    public User getUserByIdAndName(Map<String,String> map);

    //更新用户
    //根据id更新为传入的对象值
    public int updateUser(User user);

    //添加用户
    public int addUser(User user);

    //删除用户
    //根据id删除用户
    public int deleteUser(int id);

    //添加用户 并获取自增的主键
    void inserUser(User user);
}
