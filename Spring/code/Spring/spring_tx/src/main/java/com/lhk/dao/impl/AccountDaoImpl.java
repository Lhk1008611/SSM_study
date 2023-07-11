package com.lhk.dao.impl;

import com.lhk.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-11:30
 */
public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void out(String outName, BigDecimal money) {
        jdbcTemplate.update("update account set money =money-? where name =?",money,outName);
    }

    @Override
    public void in(String inName, BigDecimal money) {
        jdbcTemplate.update("update account set money =money+? where name =?",money,inName);

    }
}
