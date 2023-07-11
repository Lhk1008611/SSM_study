package com.lhk.staticProxy;

public class CalculatorImpl implements Calculator{
    @Override
    public int add(int numberOne, int numberTwo) {
        int result = numberOne + numberTwo;
        return result;
    }

    @Override
    public int sub(int numberOne, int numberTwo) {
        int result = numberOne - numberTwo;
        return result;
    }

    @Override
    public int mul(int numberOne, int numberTwo) {
        int result = numberOne * numberTwo;
        return result;
    }

    @Override
    public int div(int numberOne, int numberTwo) {
        int result = numberOne / numberTwo;
        return result;
    }
}
