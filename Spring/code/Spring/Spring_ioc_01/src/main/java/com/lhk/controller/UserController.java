package com.lhk.controller;

import com.lhk.pojo.User;
import com.lhk.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author TheMutents
 * @creat on 2021-12-12-18:17
 */
public class UserController {

    public static void main(String[] args) {
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\Workspace_IDEA\\Spring\\Spring_ioc_01\\src\\main\\resources\\applicationContext.xml");
//        UserService userService = (UserService) applicationContext.getBean("userService");
        //当配置文件中配置一个对象时可以使用如下方法获得对象，配置有多个对象时可用getBean方法通过id的方法获取
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
    }
}
