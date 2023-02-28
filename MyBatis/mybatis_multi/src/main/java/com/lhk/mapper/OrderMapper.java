package com.lhk.mapper;

import com.lhk.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-27-11:40
 */
public interface OrderMapper {
    //多表查询
    List<Order> queryOrder();

    /**
     * 多对一分步查询
     * @return
     */
    List<Order> queryOrderAndUserStepOne();

    /**
     * 一对多分布查询
     * @return
     */
    List<Order> queryUserAndOrderStepTwo(@Param("userId") Integer userId);
}
