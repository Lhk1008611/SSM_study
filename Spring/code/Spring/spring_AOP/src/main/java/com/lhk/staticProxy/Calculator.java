package com.lhk.staticProxy;

public interface Calculator {
    /**
     * 加法
     * @param numberOne
     * @param numberTwo
     * @return
     */
    int add(int numberOne,int numberTwo);

    /**
     * 减法
     * @param numberOne
     * @param numberTwo
     * @return
     */
    int sub(int numberOne,int numberTwo);

    /**
     * 乘法
     * @param numberOne
     * @param numberTwo
     * @return
     */
    int mul(int numberOne,int numberTwo);

    /**
     * 除法
     * @param numberOne
     * @param numberTwo
     * @return
     */
    int div(int numberOne,int numberTwo);

}
