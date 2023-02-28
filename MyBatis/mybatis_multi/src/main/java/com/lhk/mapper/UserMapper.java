package com.lhk.mapper;

import com.lhk.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-27-10:52
 */
public interface UserMapper {

    List<User> queryUser();

    /**
     * 多对一分步查询
     * @return
     */
    User queryOrderAndUserStepTwo(@Param("userId") Integer userId);


    /**
     * 一对多分布查询
     * @param userId
     * @return
     */
    User queryUserAndOrderStepOne(@Param("userId") Integer userId);
}
