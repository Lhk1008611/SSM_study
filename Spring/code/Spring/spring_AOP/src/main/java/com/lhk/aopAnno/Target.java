package com.lhk.aopAnno;

import org.springframework.stereotype.Component;

/**
 * @author TheMutents
 * @creat on 2021-12-24-13:40
 */

@Component("target")
public class Target implements TargetInterface {


    @Override
    public void save() {
            int i = 1/0;
            System.out.println("执行save()方法......");
    }
}
