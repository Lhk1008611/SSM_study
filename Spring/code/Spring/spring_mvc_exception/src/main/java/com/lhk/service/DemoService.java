package com.lhk.service;

import com.lhk.exception.MyException;

import java.io.FileNotFoundException;

/**
 * @author TheMutents
 * @creat on 2021-12-23-11:08
 */
public interface DemoService {
    void show1();

    void show2();

    void show3() throws FileNotFoundException;

    void show4();

    void show5() throws MyException;
}
