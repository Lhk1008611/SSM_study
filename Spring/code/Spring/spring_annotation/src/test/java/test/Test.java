package test;

import com.lhk.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void TestAutowire(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
        UserController userController = ioc.getBean(UserController.class);
        userController.save();
    }
}
