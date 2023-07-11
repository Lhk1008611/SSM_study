package com.lhk.service;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-13:40
 */
public interface AccountService {
   /**
    * 转账业务
    * @param outName
    * @param inName
    * @param money
    */
   void transfer(String outName, String inName, BigDecimal money);
}
