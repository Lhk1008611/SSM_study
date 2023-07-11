package com.lhk.aopAnno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * @author TheMutents
 * @creat on 2021-12-24-15:02
 */
@Component("myAspect")
@Aspect  //标注当前MyAspect是一个切面
public class MyAspect {

    /**
     * 切点表达式的抽取
     * 定义一个方法，在该方法上使用@Pointcut注解定义切点表达式
     * 然后在增强注解中进行引用
     */
    @Pointcut("execution(* com.lhk.aopAnno.*.*(..))")
    public void myPointCut(){ }

    //配置前置增强
    @Before("myPointCut()")
    public void before(){
        System.out.println("前置通知......");
    }

    @AfterReturning("MyAspect.myPointCut()")
    public void afterReturning(){
        System.out.println("返回通知......");
    }

    //ProceedingJoinPoint :正在执行的连接点 = 切点
    @Around("myPointCut()")
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

    @AfterThrowing("myPointCut()")
    public void afterThrowing(){
        System.out.println("异常通知......");
    }

    @After("execution(* com.lhk.aopAnno.*.*(..))")
    public void after(){
        System.out.println("最终增强.....");
    }
}
