package com.lhk.ActiveProxy.cglig;

import com.lhk.ActiveProxy.jdk.TargetInterface;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TheMutents
 * @creat on 2021-12-24-13:44
 */
public class ProxyTest {
    //基于cjlib的动态代理的实现
    public static void main(final String[] args) {

        //创建目标对象
        final Target target = new Target();

        //创建增强对象
        final Advice advice = new Advice();


        /**
         * 该方法返回的就是一个基于cjlib的动态生成的代理对象
         */
        //1.创建cjlib提供的增强器
        Enhancer enhancer = new Enhancer();
        //2.设置父类（目标）
        enhancer.setSuperclass(Target.class);
        //3.设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //前置增强
                advice.before();
                //执行目标方法
                Object invoke = method.invoke(target, args);
                //后置增强
                advice.after();
                return null;
            }
        });
        //4.创建代理对象
        Target proxy = (Target) enhancer.create();

        proxy.save();
    }
}
