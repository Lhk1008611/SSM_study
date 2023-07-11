package com.lhk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author TheMutents
 * @creat on 2021-12-15-14:17
 */
//标志该类时Spring的核心配置类
@Configuration
//使用注解配置组件扫描
@ComponentScan("com.lhk")
//使用注解导入其他配置类
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
