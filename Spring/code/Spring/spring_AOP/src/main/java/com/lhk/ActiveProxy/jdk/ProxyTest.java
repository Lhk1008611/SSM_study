package com.lhk.ActiveProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author TheMutents
 * @creat on 2021-12-24-13:44
 */
public class ProxyTest {
    //基于jdk动态代理的实现
    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //创建增强对象
        final Advice advice = new Advice();
        /**
         * newProxyInstance()有三个参数
         * 该方法返回的就是一个动态生成的代理对象
         */
        TargetInterface proxyInstance = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//参数一：目标对象的类加载器
                target.getClass().getInterfaces(),//参数二：目标对象的接口字节码对象数组
                new InvocationHandler() { //参数三：实现接口InvocationHandler
                    //通过执行invoke()方法来 调用代理对象的任何方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();
                        //执行目标方法
                        Object invoke = method.invoke(target, args);
                        //后置增强
                        advice.after();
                        return invoke;
                    }
                }
        );

        proxyInstance.save();


    }
}
