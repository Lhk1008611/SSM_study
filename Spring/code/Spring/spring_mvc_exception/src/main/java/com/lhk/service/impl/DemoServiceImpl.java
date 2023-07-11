package com.lhk.service.impl;

import com.lhk.exception.MyException;
import com.lhk.service.DemoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author TheMutents
 * @creat on 2021-12-23-11:09
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public void show1() {
        System.out.println("抛出类型转换异常...");
        Object str = "lhk1008611";
        Integer integer = (Integer) str;
    }

    @Override
    public void show2() {
        System.out.println("抛出除以0异常...");
        int i=1/0;
    }

    @Override
    public void show3() throws FileNotFoundException {
        System.out.println("文件找不到异常异常...");
        FileInputStream is = new FileInputStream("D:/xxx/xxx/xxx.text");
    }

    @Override
    public void show4() {
        System.out.println("空指针异常....");
        String str = null;
        str.length();
    }

    @Override
    public void show5() throws MyException {
        System.out.println("自定义异常....");
        throw new MyException();
    }
}
