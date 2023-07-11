package com.lhk.controller;

import com.lhk.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author TheMutents
 * @creat on 2021-12-23-10:51
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/show")
    public String Show(){
        System.out.println("show running..........");
        demoService.show1();
        //demoService.show2();
        //demoService.show3();
        //demoService.show4();
        //demoService.show5();
        return "index";
    }
}
