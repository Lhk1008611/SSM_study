package com.lhk.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类
 * @author TheMutents
 * @creat on 2021-12-24-15:02
 */
public class MyAspect {

    public void before(){
        System.out.println("前置通知......");
    }

    public void afterReturning(){
        System.out.println("返回通知......");
    }

    //ProceedingJoinPoint :正在执行的连接点 = 切点
    public Object around(ProceedingJoinPoint pjp) {
        Object proceed = null;//切点方法
        try {
            System.out.println("环绕——>前置通知");
            proceed = pjp.proceed();
            System.out.println("环绕——>返回通知");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("环绕——>异常通知");
        }finally {
            System.out.println("环绕——>后置通知");
        }
        return proceed;
    }

    public void afterThrowing(){
        System.out.println("异常通知......");
    }

    public void after(){
        System.out.println("后置通知.....");
    }
}
