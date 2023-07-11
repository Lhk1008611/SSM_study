package com.lhk.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TheMutents
 * @creat on 2021-12-22-21:27
 */
@Component
public class MyInterceptor2 implements HandlerInterceptor {

    @Override
    //preHandle()方法是 在目标方法执行前 执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle().......222222");
        return true;
    }

    @Override
    //postHandle()方法是 在目标方法执行后 视图返回之前 执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle()........222222");
    }

    //afterCompletion()方法是在 整个访问资源流程执行完毕后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion().....222222");
    }
}
