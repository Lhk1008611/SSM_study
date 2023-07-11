package com.lhk.controller;

import com.lhk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-11:01
 */
public class AccountController {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transfer("jack","lhk",new BigDecimal(1000));
    }
}
