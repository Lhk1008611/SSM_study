package com.lhk.listener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author TheMutents
 * @creat on 2021-12-15-19:59
 */
public class ContextLoaderListener implements ServletContextListener {
    /**
     * 使用监听器ServletContextListener监听web应用的启动，可以在web应用启动时，加载Spring配置文件
     * 创建应用上下文对象ApplicationContext，并将其存入最大的域servletContext域中，这样就可以在任
     * 意位置从域中获取应用上下文对象ApplicationContext
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        //读取web.xml中的全局参数
        String appContextXML = servletContext.getInitParameter("contextConfigLocation");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(appContextXML);

        //将应用上下文对象ApplicationContext入最大的域servletContext域中
        servletContext.setAttribute("appContext",applicationContext);
        System.out.println("Spring容器创建完毕......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
