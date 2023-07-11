package com.lhk.dao.impl;

import com.lhk.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-11:30
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void out(String outName, BigDecimal money) {
        jdbcTemplate.update("update account set money =money-? where name =?",money,outName);
    }

    @Override
    public void in(String inName, BigDecimal money) {
        jdbcTemplate.update("update account set money =money+? where name =?",money,inName);

    }
}
