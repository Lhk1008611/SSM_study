package com.lhk.ActiveProxy.jdk;

/**
 * 该类实现增强功能的方法
 * @author TheMutents
 * @creat on 2021-12-24-13:42
 */

public class Advice {
    //增强方法
    public void before(){
        System.out.println("前置增强....");
    }
    public void after(){
        System.out.println("后置增强....");
    }

}
