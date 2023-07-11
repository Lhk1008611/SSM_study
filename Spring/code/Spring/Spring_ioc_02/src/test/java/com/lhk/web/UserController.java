package com.lhk.web;

import com.lhk.config.SpringConfiguration;
import com.lhk.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author TheMutents
 * @creat on 2021-12-15-11:34
 */
public class UserController {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userservice = (UserService)applicationContext.getBean("userservice");
        userservice.save();
        applicationContext.close();//手动关闭容器
    }
}
