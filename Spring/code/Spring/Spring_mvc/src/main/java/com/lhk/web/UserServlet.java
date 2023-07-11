package com.lhk.web; /**
 * @author TheMutents
 * @creat on 2021-12-15-19:29
 */

import com.lhk.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取ServletContext域对象
        ServletContext servletContext = this.getServletContext();
        //从servletContext域中取出ApplicationContext应用上下文对象
//        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("appContext");

        //自定义getWebApplicationContext()
//        ApplicationContext applicationContext = WebApplicationContextUtils.getApplicationContext(servletContext);

        //Spring中对上面功能进行了封装,需要导入坐标spring-web
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
    }

}
