

## 1. IOC 容器

- ioc：inversion of control 反转控制（思想）

  - 将对象由主动创建转变为由 spring ioc 容器来提供对象，转换了对象的控制权

- DI：dependency injection 依赖注入（IOC的具体实现方式）

- IOC 的 spring 实现

  ![image-20230305114748376](E:\study\SSM_study\Spring\image\image-20230305114748376.png)

  - 获取容器

    1. 根据 bean 的类型获取 bean 对象

       ```java
       FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\Workspace_IDEA\\Spring\\Spring_ioc_01\\src\\main\\resources\\applicationContext.xml");
       //当配置文件中配置一个对象时可以使用如下方法获得对象，配置有多个对象时可用getBean方法通过id的方法获取
       UserService userService = applicationContext.getBean(UserService.class);
       userService.save();
       ```

    2. 根据 bean ID 获取 bean 对象（常用）

       ```java
       ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
       //通过 bean 的 ID 获得 bean 对象
       UserService userService = (UserService) applicationContext.getBean("userService");
       userService.save();
       ```

    3. 根据 bean 的 ID 和类型获取bean对象

       ```java
       UserService userService = applicationContext.getBean("userService",UserService.class);
       ```

    - 配置文件

      ```xml
      <!--通过与Service的构造器映射实现对Bean的依赖注入 -->
              <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl">
                      <constructor-arg name="userDao" ref="userDao"></constructor-arg>
              </bean>
      ```

    - 当某个接口有唯一的实现类，可以通过接口类型来获取到实现类的 bean 对象

      - 结论：在根据类型来获取 bean 时，在**满足 bean 唯一性**的前提下，其实是看 `` bean对象.instanceof(指定的类型)`` 的返回值，返回值为真就说明可以使用指定的类型拿到 bean 对象
        - 指定的类型包括 ：bean 的类型、bean 所实现的接口类型、bean 所继承的类的类型

  - 基于 xml 依赖注入

    1. 通过 setter 注入

       - property 标签：
         - name：设置属性名
         - ref：引用 IOC 容器中的 bean 对象，用于设置实体类型属性
         - value：设置属性值

       ```xml
       <!-- 通过与Service的set方法映射实现对Bean的依赖注入 -->
               <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl">
                       <property name="userDao" ref="userDao"></property>
               </bean>
       ```

    2. 通过构造器注入

       - constructor-arg 标签：

         - name：设置属性名

         - ref：引用 IOC 容器中的 bean 对象，用于设置实体类型属性

         - value：设置属性值

         - type：设置属性类型

         - index： 属性在构造器函数的位置索引

           ````xml
           <!--通过与Service的构造器映射实现对Bean的依赖注入 -->
                   <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl">
                           <constructor-arg name="userDao" ref="userDao"></constructor-arg>
                   </bean>
           ````

    3. 特殊值注入

       - 注入 null

         ```xml
                <bean id="user2" class="com.lhk.pojo.User">
                         <property name="name" value="TheMutents"></property>
                         <!--  给属性注入 null  -->            
                         <property name="addr">
                                 <null/>
                         </property>
                 </bean>
         ```

       - 注入特殊字符

         1. 使用 xml 实体处理特殊字符

            - 例如 ：``` &lt;``` 等价于```< ``` ，``` &gt;``` 等价于 ``` >```

              ````xml
              <property name="name" value="&lt;Lhk&gt;"></property>
              ````

         2. 使用 CDATA 节处理特殊字符

            - 在 CDATA 节里面的内容不会当做 xml 标签进行解析，而是进行原样解析，所以可以在 CDATA 节里面写任意内容

              ```xml
                              <property name="addr">
                                      <value>
                                              <![CDATA[<湖南>]]>
                                      </value>
                              </property>
              ```

    4. 注入实体类类型

       - 通过 ref 属性引用外部实体类 bean 对象

         ```xml
                 <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl">
                         <constructor-arg name="userDao" ref="userDao"></constructor-arg>
                 </bean>
         ```

       - 通过内部 bean 注入

         - 内部 bean 对象不能被 IOC 容器管理

         ```xml
                 <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl">
                         <constructor-arg name="userDao">
                                 <bean id="userDao" class="com.lhk.dao.daoimpl.UserDaoImpl">
                                         <!-- 注入普通数据类型 -->
                                         <property name="userName" value="lhk"></property>
                                         <property name="age" value="22"></property>
                                         <!-- 注入集合数据类型 -->
                                         <property name="stringList">
                                                 <list >
                                                         <value>aaaa</value>
                                                         <value>bbbb</value>
                                                         <value>cccc</value>
                                                 </list>
                                         </property>
                                         <property name="userMap">
                                                 <map>
                                                         <entry key="u1" value-ref="user1"></entry>
                                                         <entry key="u2" value-ref="user2"></entry>
                                                 </map>
                                         </property>
         
                                         <property name="properties">
                                                 <props>
                                                         <prop key="prop1">ppp1</prop>
                                                         <prop key="prop2">ppp2</prop>
                                                         <prop key="prop3">ppp3</prop>
                                                 </props>
                                         </property>
                                 </bean>
                         </constructor-arg>
                 </bean>
         ```

    5. 注入数组

       - 若数组中存储的是实体类类型数据，则使用 ``` <ref>``` 引用实体类对象

       - 若数组中存储的是字面量类型数据，则使用``` <value>``` 设置属性值

         ```xml
                         <property name="stringArray">
                                 <array >
                                         <value>aaaa</value>
                                         <value>bbbb</value>
                                         <value>cccc</value>
                                 </array>
                         </property>
         ```

    6. 注入 list 集合

       - 若 list 集合中存储的是实体类类型数据，则使用 ``` <ref>``` 引用实体类对象

       - 若 list 集合中存储的是字面量类型数据，则使用``` <value>``` 设置属性值

         ```xml
                         <!-- 注入集合数据类型 -->
                         <property name="UserList">
                                 <list >
                                         <ref bean="user1"></ref>
                                         <ref bean="user2"></ref>
                                 </list>
                         </property>
         ```

       - 使用 util 标签设置 list 集合的bean

         ```xml
                         <!-- 注入集合数据类型 -->
                         <property name="UserList" ref="users">
         ```

         - 需要引入 util 约束

           ```xml
                   <bean id="user1" class="com.lhk.pojo.User">
                           <property name="name" value="&lt;Lhk&gt;"></property>
                           <property name="addr">
                                   <value>
                                           <![CDATA[<湖南>]]>
                                   </value>
                           </property>
                   </bean>
                   <bean id="user2" class="com.lhk.pojo.User">
                           <property name="name" value="TheMutents"></property>
                           <!--  给属性注入 null  -->
                           <property name="addr">
                                   <null />
                           </property>
                   </bean>
           		<util:list id="users">
                           <ref bean="user1"></ref>
                           <ref bean="user2"></ref>
                   </util:list>
           ```

    7. 注入 map 集合

       - map 标签：
         - 一个 entry 标签代表 map 中的一个键值对
           - key：设置字面量类型的 key 值
           - key-ref：设置实体类属性的 key 值
           - value：设置字面量类型的 value 值
           - value-ref：设置实体类属性的 value 值

       ```xml
                       <property name="userMap">
                               <map>
                                       <entry key="u1" value-ref="user1"></entry>
                                       <entry key="u2" value-ref="user2"></entry>
                               </map>
                       </property>
       ```

       - 使用 util 标签设置 map 集合的bean

         ```xml
                         <property name="userMap" ref="usermap"></property>
         ```

         ```xml
                 <util:map id="usermap">
                         <entry key="u1" value-ref="user1"></entry>
                         <entry key="u2" value-ref="user2"></entry>
                 </util:map>
         ```

    8. p 命名标签

       - p:userDao-ref：引用 bean 对象来设置属性

       - p:userDao：设置字面类型的属性

         ```xml
         <!-- p 命名空间:xmlns:p="http://www.springframework.org/schema/p"-->
                 <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl" p:userDao-ref="userDao"></bean>
         ```



## 2. 管理数据源和引入外部 properties 文件

1. 在 spring 的配置文件中，引入外部的 properties 文件

   ```xml
       <!--加载外部的properties文件-->
       <context:property-placeholder location="jdbc.properties"></context:property-placeholder>
   ```

2. 配置数据源

   1. 无需引入外部 properties 文件，直接给数据源属性注入值

      ```xml
          <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
              <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
              <property name="url" value="jdbc:mysql://localhost:3306/test"></property>
              <property name="username" value="root"></property>
              <property name="password" value="123456"></property>
          </bean>
      ```

   2. 使用spel表达式 ( ${} ) 引入properties文件内的内容，配置数据源

      ````xml
          <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
              <property name="driverClass" value="${jdbc.driver}"></property>
              <property name="jdbcUrl" value="${jdbc.url}"></property>
              <property name="user" value="${jdbc.username}"></property>
              <property name="password" value="${jdbc.password}"></property>
          </bean>
      ````

   3. 通过配置类配置数据源

      ```java
      package com.lhk.config;
      
      /**
       * @author TheMutents
       * @creat on 2021-12-15-14:34
       */
      
      import com.mchange.v2.c3p0.ComboPooledDataSource;
      import org.springframework.beans.factory.annotation.Value;
      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.PropertySource;
      import javax.sql.DataSource;
      import java.beans.PropertyVetoException;
      
      //使用注解导入外部的properties文件
      @PropertySource("jdbc.properties")
      public class DataSourceConfiguration {
          @Value("${jdbc.driver}")
          private String driver;
          @Value("${jdbc.url}")
          private String url;
          @Value("${jdbc.username}")
          private String userName;
          @Value("${jdbc.password}")
          private String password;
      
      
          @Bean("dataSource") //将返回值以指定的名字存储到Spring容器中
          public DataSource getDataSource() throws PropertyVetoException {
              ComboPooledDataSource dataSource = new ComboPooledDataSource();
              dataSource.setDriverClass(driver);
              dataSource.setJdbcUrl(url);
              dataSource.setUser(userName);
              dataSource.setPassword(password);
              return dataSource;
          }
      }
      
      ```

      ```java
      package com.lhk.config;
      
      import org.springframework.context.annotation.ComponentScan;
      import org.springframework.context.annotation.Configuration;
      import org.springframework.context.annotation.Import;
      
      /**
       * @author TheMutents
       * @creat on 2021-12-15-14:17
       */
      //标志该类是Spring的核心配置类
      @Configuration
      //使用注解配置组件扫描
      @ComponentScan("com.lhk")
      //使用注解导入其他配置类
      @Import({DataSourceConfiguration.class})
      public class SpringConfiguration {
      
      }
      
      ```



## 3. bean 的作用域

1. 单例

   - 通过 ioc 容易获取到的 bean 对象永远是同一个

   - 通过 scope="singleton" 进行配置

   - bean 对象不设置作用域（scope）时默认为单例

     ```xml
     <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl" scope="singleton">
     	<constructor-arg name="userDao" ref="userDao"></constructor-arg>
     </bean>
     ```

2. 多例

   - 通过 ioc 容易获取到的 bean 对象不是同一个，每一个都是一个新的 bean 对象

   - 通过 scope="prototype" 进行配置

     ```xml
     <bean id="userService" class="com.lhk.service.serviceImpl.UserServiceImpl" scope="prototype">
     	<constructor-arg name="userDao" ref="userDao"></constructor-arg>
     </bean>
     ```



## 4. bean 的生命周期

- bean 的生命周期

  1. 实例化，执行无参构造方法
  2. 依赖注入，执行相关 set 方法
  3. 初始化 ，需要通过 bean 标签的 init-method 属性指定初始化方法
  4. 销毁，需要通过 bean 标签的 destroy-method 属性指定销毁方法，在 ioc 容器关闭时执行销毁方法
     - 若 bean 是单例的，则生命周期的前三个步骤会在获取 ioc 容器时执行
     - 若 bean 是多例的，则生命周期的前三个步骤会在获取 bean 对象时执行

- bean 的具体生命周期

  1. bean 对象创建（调用无参构造）
  2. 给 bean 对象设置属性
  3. bean 对象初始化之前操作（由 bean 的后置处理器负责）
  4. bean 对象初始化，需要通过 bean 标签的 init-method 属性指定初始化方法
  5. bean 对象初始化之后操作（由 bean 的后置处理器负责）
  6. bean 对象就绪可以使用
  7. bean 对象销毁，需要通过 bean 标签的 destroy-method 属性指定销毁方法
  8. ioc 容器关闭

- 后置处理器（了解）

  ```java
  package com.lhk.pojo;
  
  import org.springframework.beans.BeansException;
  import org.springframework.beans.factory.config.BeanPostProcessor;
  
  public class MyBeanPostProcessor implements BeanPostProcessor {
  
      //bean 对象初始化之前操作
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
          return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
      }
  
      //bean 对象初始化之后操作
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
          return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
      }
  }
  
  ```

  - 需要将后置处理器配置到 ioc 容器中，且后置处理器是针对所有的 bean 都生效，即所有的 bean 都会执行后置处理器的额外操作

    ```xml
            <bean id="myBeanPostProcessor" class="com.lhk.pojo.MyBeanPostProcessor"></bean>
    ```

    



## 5. FactoryBean

- FactoryBean 是spring 提供的一种整合第三方框架的常用机制

  ```java
  package com.lhk.factory;
  
  import com.lhk.pojo.User;
  import org.springframework.beans.factory.FactoryBean;
  
  public class UserFactoryBean implements FactoryBean<User> {
      @Override
      public User getObject() throws Exception {
          return new User();
      }
  
      @Override
      public Class<?> getObjectType() {
          return User.class;
      }
  }
  
  ```

  - 将 FactoryBean 配置为一个 bean 时，是直接将 getObject() 方法返回的对象交给 ioc 容器管理，这样省去了中间繁琐的创建对象的过程

    ```xml
            <!-- 直接拿到 User 类型的 bean -->
            <bean id="userFactoryBean" class="com.lhk.factory.UserFactoryBean"></bean>
    ```



## 6. 基于 xml 的自动装配

- 自动装配：根据指定的策略，在 ioc 容器中自动匹配某一个 bean 注入到指定 bean 中的类类型或接口类型依赖

- 自动装配策略  ( autowire )

  1. byName：根据属性的属性名匹配 bean 注入到属性中

     ```xml
         <bean id="userController" class="com.lhk.controller.UserController" autowire="byName"></bean>
     
         <bean id="userService" class="com.lhk.service.impl.UserServiceImpl" autowire="byName"></bean>
     
         <bean id="userDao" class="com.lhk.dao.impl.UserDaoImpl" autowire="byName"></bean>
     ```

     ![image-20230308204826601](C:\Users\lhk\AppData\Roaming\Typora\typora-user-images\image-20230308204826601.png)

  2. byType：根据属性的类型匹配 bean 注入到属性中

     - 根据类型自动装配需要满足 bean 唯一，才能注入成功

     ```xml
         <bean id="userController" class="com.lhk.controller.UserController" autowire="byType"></bean>
     
         <bean id="userService" class="com.lhk.service.impl.UserServiceImpl" autowire="byType"></bean>
     
         <bean id="userDao" class="com.lhk.dao.impl.UserDaoImpl" autowire="byType"></bean>
     ```

  3. no：不自动装配，使用默认值

  4. default：不自动装配，使用默认值

 

## 7. 基于注解的 bean 管理

- 标记与扫描

  - 注解
    - 注解本身不能执行具体功能，仅仅用于标记，具体功能是框架检测到有注解标记的位置，然后针对这个位置按照注解标记的功能，执行相应的 Java 代码实现对应的功能 
  - 扫描
    - spring 通过扫描的方式检测注解标记的地方，然后根据注解进行后续的操作

- 标识组件的常用注解

  - @Component：标识为普通组件 (bean)
  - @Controller：标识为控制层组件 (bean)
  - @Service：标识为业务层组件 (bean)
  - @Repository：标识为持久层组件 (bean)
    - 这四个注解功能一致，不通过的名称是为了让开发者更容易分辨组件的作用，提高代码的可读性

- 配置组件扫描

  ```xml
      <!-- 配置组件扫描 -->
      <context:component-scan base-package="com.lhk"></context:component-scan>
  ```

  - 子标签

    - context:exclude-filter：扫描时排除某些包或者标签
    - context:include-filter：扫描时只扫描某些包或者标签，需要 ` use-default-filters="false"`

    ```xml
        <context:component-scan base-package="com.lhk" use-default-filters="false">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    ```

    