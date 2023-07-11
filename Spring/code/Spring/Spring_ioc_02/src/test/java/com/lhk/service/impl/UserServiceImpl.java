package com.lhk.service.impl;

import com.lhk.dao.UserDao;
import com.lhk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author TheMutents
 * @creat on 2021-12-15-11:27
 */
//<bean id="userservice" class="com.lhk.service.impl.UserServiceImpl">
//使用如下注解
//@Component("userservice")
    @Service("userservice")  //配置Service对象供web层使用
    @Scope("singleton")  //配置Service对象的作用域
public class UserServiceImpl implements UserService {
//    <property name="userDao" ref="userdao"></property>
//    使用如下注解注入UserDao
//    @Autowired //自动装配，按照注入数据的数据类型从Spring容器中进行装配，可单独使用
//    @Qualifier("userdao") //传入UserDao的id，根据id值从容器中进行匹配，但是Qualifier需要结合Autowired使用
    @Resource(name = "userdao") //Resource相当于Qualifier结合Autowired
    private UserDao userDao;

//    注入普通类型数据,可使用spel表达式进行注入
    @Value("${jdbc.driver}")
    private String str;


    //使用xml配置文件注入对象则需要setXXX()方法
    //使用注解注入对象则可不写setXXX()方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void save() {
        System.out.println(str);
        userDao.save();
    }

    @PostConstruct   //配置初始化方法
    public void init(){
        System.out.println("Service初始化方法执行");
    }

    @PreDestroy  //配置销毁方法
    public void destory(){
        System.out.println("Service销毁方法执行");
    }
}
