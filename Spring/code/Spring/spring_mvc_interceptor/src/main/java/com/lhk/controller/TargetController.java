package com.lhk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author TheMutents
 * @creat on 2021-12-22-21:06
 */
@Controller
public class TargetController {

    @RequestMapping("/target")
    public ModelAndView show(){
        System.out.println("controller 执行....");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","lhk1008611");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
