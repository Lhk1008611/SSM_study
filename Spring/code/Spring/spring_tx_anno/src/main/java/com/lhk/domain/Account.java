package com.lhk.domain;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-11:01
 */
public class Account {
    private String name;
    private BigDecimal money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
