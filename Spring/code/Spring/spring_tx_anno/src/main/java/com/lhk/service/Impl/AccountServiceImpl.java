package com.lhk.service.Impl;

import com.lhk.dao.AccountDao;
import com.lhk.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-13:42
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void transfer(String outName, String inName, BigDecimal money) {
        accountDao.out(outName,money);
        int i=1/0;
        accountDao.in(inName,money);
    }
}
