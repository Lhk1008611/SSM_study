package com.lhk.domain;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-20-10:18
 */
//ValueObject
public class VO {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "VO{" +
                "userList=" + userList +
                '}';
    }
}
