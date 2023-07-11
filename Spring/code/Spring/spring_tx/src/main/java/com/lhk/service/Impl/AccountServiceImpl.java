package com.lhk.service.Impl;

import com.lhk.dao.AccountDao;
import com.lhk.service.AccountService;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-13:42
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String outName, String inName, BigDecimal money) {

        accountDao.out(outName,money);
        int i=1/0;
        accountDao.in(inName,money);
    }
}
