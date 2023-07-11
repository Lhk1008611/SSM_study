package com.lhk.staticProxy;

public class CalculatorProxy implements Calculator{

    CalculatorImpl target;

    public CalculatorProxy(CalculatorImpl target) {
        this.target = target;
    }

    @Override
    public int add(int numberOne, int numberTwo) {
        int result =0;
        try {
            System.out.println("前置通知");
            result = target.add(numberOne,numberTwo);
            System.out.println("返回通知");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常通知");
        }finally {
            System.out.println("后置通知");
        }
        return result;
    }

    @Override
    public int sub(int numberOne, int numberTwo) {
        int result =0;
        try {
            System.out.println("前置通知");
            result = target.sub(numberOne,numberTwo);
            System.out.println("返回通知");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常通知");
        }finally {
            System.out.println("后置通知");
        }
        return result;
    }

    @Override
    public int mul(int numberOne, int numberTwo) {
        int result =0;
        try {
            System.out.println("前置通知");
            result = target.mul(numberOne,numberTwo);
            System.out.println("返回通知");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常通知");
        }finally {
            System.out.println("后置通知");
        }
        return result;
    }

    @Override
    public int div(int numberOne, int numberTwo) {
        int result =0;
        try {
            System.out.println("前置通知");
            result = target.div(numberOne,numberTwo);
            System.out.println("返回通知");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常通知");
        }finally {
            System.out.println("后置通知");
        }
        return result;
    }

    public static void main(String[] args) {
        CalculatorProxy calculatorProxy = new CalculatorProxy(new CalculatorImpl());
        calculatorProxy.div(3,2);
    }
}
