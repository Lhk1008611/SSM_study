package com.lhk.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

/**
 * @author TheMutents
 * @creat on 2021-12-21-15:32
 */
public class JdbcTemplateTest {

    /**
     * 使用Spring产生JdbcTemplate模板对象，进行crud操作
     */
    @Test
    public void test2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        int i = jdbcTemplate.update("insert into account values(?,?)", "LHK11", 2021.12);
        System.out.println(i);
    }



    /**
     * Template的简单使用
     * @throws PropertyVetoException
     */
    @Test
    public  void test() throws PropertyVetoException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("000422");

        //使用Template前需要设置数据源
        jdbcTemplate.setDataSource(dataSource);

        //执行crud操作
        int i = jdbcTemplate.update("insert into account values(?,?)", "lhk", 2000);
        System.out.println(i);

    }
}
