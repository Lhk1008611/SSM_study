package com.lhk.controller;

import com.lhk.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TheMutents
 * @creat on 2021-12-23-10:51
 */
@ControllerAdvice
public class DemoController_1 {

    @Autowired
    private DemoService demoService;

    @ExceptionHandler(ClassCastException.class)
    public String Show(Throwable ex, Model model) {
        System.out.println("遇到异常时进行一些处理.....");
        demoService.show1();
        model.addAttribute("ex", ex);//将异常信息存入请求域
        return "error";//出现 ClassCastException 异常时跳转到 error 页面
    }
}
