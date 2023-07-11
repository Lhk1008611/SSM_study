package com.lhk.ActiveProxy.jdk;

/**
 * @author TheMutents
 * @creat on 2021-12-24-13:40
 */
public class Target implements TargetInterface{
    @Override
    public void save() {
        System.out.println("执行save()方法......");
    }
}
