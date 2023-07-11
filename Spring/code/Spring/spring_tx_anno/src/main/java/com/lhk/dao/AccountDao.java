package com.lhk.dao;

import java.math.BigDecimal;

/**
 * @author TheMutents
 * @creat on 2021-12-25-11:07
 */
public interface AccountDao {

    /**
     * 转出钱
     * @param outName
     * @param money
     */
    void out(String outName, BigDecimal money);

    /**
     * 转入钱
     * @param inName
     * @param money
     */
    void in(String inName, BigDecimal money);

}
