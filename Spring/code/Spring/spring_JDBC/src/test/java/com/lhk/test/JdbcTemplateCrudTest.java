package com.lhk.test;

import com.lhk.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-21-16:05
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCrudTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 测试对数据库进行修改操作
     */
    @Test
    public void testUpdate() {
        jdbcTemplate.update("update account set money=? where name=?",1008.16,"lhk");
    }

    /**
     * 测试对数据库进行删除操作
     */
    @Test
    public void testDelete(){
        jdbcTemplate.update("delete from account where name=?","LHK11");
    }

    /**
     * 查询多条记录
     */
    @Test
    public void testQueryForList(){
        List<Account> accountList = jdbcTemplate.query("select * from account ",new BeanPropertyRowMapper<Account>(Account.class));
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    /**
     * 查询一条实体记录
     */
    @Test
    public void testQueryForOne(){
        Account tom = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), "tom");
        System.out.println(tom);
    }

    /**
     * 查询特殊值
     */
    @Test
    public void testQueryForOne1(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        System.out.println(aLong);
    }
}
