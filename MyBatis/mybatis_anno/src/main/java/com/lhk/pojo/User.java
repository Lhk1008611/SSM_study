package com.lhk.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-10-12-23:43
 */
public class User {
    private int id;
    private String name;
    private String pwd;
    private Date birthday;
    //一个用户对应多个订单
//    private List<Order> orderList;

    //一个用户对应多个角色
    private List<Role> roleList;

    //无参构造
    public User(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd; }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    public List<Order> getOrderList() {
//        return orderList;
//    }
//
//    public void setOrderList(List<Order> orderList) {
//        this.orderList = orderList;
//    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday=" + birthday +
                ", roleList=" + roleList +
                '}';
    }
}

