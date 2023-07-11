package com.lhk.aop;

/**
 * @author TheMutents
 * @creat on 2021-12-24-13:40
 */
public class Target implements TargetInterface {
    @Override
    public void save() {
//        int i = 1/0;
        System.out.println("执行save()方法......");
    }
}
