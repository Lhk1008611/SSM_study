## Spring MVC 简介

### 1. 什么是 MVC

- MVC 是一种软件架构思想，将软件按照模型、视图、控制器来划分
- M：model，模型层，指工程中的 Java bean，作用是处理数据
  - Java Bean 分两类
    1. 实体类 Bean：专门存储业务数据的
    2. 业务处理 Bean：指 service 或 Dao 对象，专门用于处理业务逻辑和数据访问
- V：view，视图层，指工程中的 html 或 jsp 等页面
  - 作用：与用户进行交互，展示数据
- C：controller，控制层，指工程中的 servlet
  - 作用：接收请求和响应浏览器



### 2. 什么是 Spring MVC

- Spring MVC 是 Spring 为**表述层**开发提供的一整套完备的解决方案
- **三层式架构**：表述层（表示层）、业务逻辑层、数据访问层
  - 表述层：前台页面 + 后台 servlet
- 特点：
  - 无缝对接 IOC 容器
  - 基于原生 servlet 实现了强大的前端控制器 DispatcherServlet，对请求和响应进行统一处理
  - 全方位覆盖表述层各细分领域，提供全面解决方案
  - 提升开发效率
  - 内部组件化程度高，配置相应组件即可使用对应的功能
  - 性能卓著，适合大型互联网项目

- 依赖

  ```xml
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.0.5</version>
      </dependency>
  ```

### 3. 配置前端控制器

- 在 web.xml 文件中配置

  ```xml
      <!--配置SpringMVC的前端控制器-->
      <servlet>
          <servlet-name>DispatcherServlet</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:spring-mvc.xml</param-value>
          </init-param>
          <!--服务器启动时就加载Servlet对象-->
          <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
          <servlet-name>DispatcherServlet</servlet-name>
          <!--任何请求都会经过该Servlet,不会匹配 *.jsp 请求-->
          <url-pattern>/</url-pattern>
      </servlet-mapping>
  ```

  - `url-pattern` 中配置 / 和 /* 的区别

    - /：匹配浏览器向服务器发送的所有请求（不包括 .jsp 的请求）
    - /*：匹配浏览器向服务器发送的所有请求（包括 .jsp 的请求）
    - 由于.jsp 的请求是需要 Tomcat 中配置的 JspServlet 去处理的，DispatcherServlet 是无法对 jsp 请求进行处理，所以在配置 DispatcherServlet 时使用 / 即可

  - `init-param`：配置加载配置文件的路径

    ```xml
    <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:spring-mvc.xml</param-value>
            </init-param>
    ```

  - `load-on-startup`：设置 DispatcherServlet 的初始化时机

    - 默认初始化是在第一次访问 DispatcherServlet  时初始化，会造成第一次访问的请求花费的时间比较长
    - 设置为 1 表示将 DispatcherServlet  初始化时机提前到服务器启动时

### 4. 配置视图解析器

- 在 spring-mvc 配置文件中进行配置

  ```xml
          <!--配置视图解析器内部资源-->
          <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                  <!-- /jsp/xxx.jsp  -->
                  <property name="prefix" value="/jsp/"></property>
                  <property name="suffix" value=".jsp"></property>
          </bean>
  ```

### 5. `@RequestMapping` 注解

1. `@RequestMapping` 的作用：

   - 将请求和处理请求的控制器关联，建立映射关系

2. `@RequestMapping`注解可以标识的位置

   1. 类上：设置映射请求的请求路径的初始信息

      ```java
      @Controller
      @RequestMapping("/user")
      public class UserController {}
      ```

   2. 方法上：设置映射请求的请求路径的具体信息

      ```java
          //通过return进行转发和重定向
          @RequestMapping(value="/quick",method = RequestMethod.GET,params = {"username"})
          public String save(){
              System.out.println("Controller save running....");
              return "success";
          }
      ```

3. `@RequestMapping` 的 value 属性

   - 设置多个 value 可以映射多个请求路径

     ```java
     @RequestMapping({"/test1","/test2"})
     ```

4. `@RequestMapping` 的 method 属性

   - method  为数组类型，用于匹配请求的请求方式（GET、POST......）

     ```java
     @RequestMapping(value = "/test1",method = {RequestMethod.GET,RequestMethod.POST})
     ```

   - 请求方式不匹配时会报 405 

5. `@RequestMapping` 的一些派生注解

   - 在`@RequestMapping` 的基础上结合请求方式而成的注解
   - `@GetMapping` `@PutMapping` `@PostMapping` `@DeleteMapping` 

6. `@RequestMapping` 的 params 属性

   - 功能：浏览器发送的请求的请求参数必须满足 params 属性的设置（所有设置都需要满足才能请求成功）

     ```java
         @RequestMapping(
                 value = "/test1",
                 method = {RequestMethod.GET,RequestMethod.POST},
                 params = {"username","!user","age=21","sex!=1"}
         )
     ```

   - params 不匹配报 400

7. `@RequestMapping` 的 headers 属性

   - 匹配请求头信息

   - 用法与 params 一致
   - headers  不匹配报 404，即找不到资源（请求头不匹配）

8. `@RequestMapping`注解可以使用 ant 风格的路径

   - 在注解的 value 属性中设置路径时可以往路径中加入一些特殊字符

     1. ?：表示任意单个字符（不包括?）

        ```java
            @RequestMapping(
                    value = "/a?b/test1",
                    method = {RequestMethod.GET,RequestMethod.POST},
                    params = {"username","!user","age=21","sex!=1"}
            )
        ```

        

     2. *：表示任意个数的任意字符（不包括?和/）

        ```java
            @RequestMapping(
                    value = "/a*b/test1",
                    method = {RequestMethod.GET,RequestMethod.POST},
                    params = {"username","!user","age=21","sex!=1"}
            )
        ```

        

     3. **：表示任意层数的任意目录

        ```java
            @RequestMapping(
                    value = "/**/test1",
                    method = {RequestMethod.GET,RequestMethod.POST},
                    params = {"username","!user","age=21","sex!=1"}
            )
        ```

9. spring MVC 路径中的占位符

   - `@PathVariable` 注解进行占位符的匹配获取工作

   - ```java
         /**
          * 获得Restful风格的参数
          * Restful风格：http://localhost:8080/Spring_mvc/user/quick16/lhk
          * 在业务方法中使用@PathVariable注解进行占位符的匹配获取工作
          * @param username
          * @throws IOException
          */
         @RequestMapping(value="/quick16/{username}")
         @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
         public void save16(@PathVariable(value = "username") String username) throws IOException {
             System.out.println(username);
         }
     ```

10. spring MVC 获取请求参数

    1. 通过 servlet API 获取请求参数

       - 在控制器方法中设置 `HttpServlet`相关的形参，即可使用 request、response 和 session 相关对象

         ```java
             /**
              * SpringMVC中获取Servlet的相关API
              * @param request
              * @param response
              * @param session
              * @throws IOException
              */
             @RequestMapping(value="quick18")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save18(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
                 System.out.println(request);
                 System.out.println(response);
                 System.out.println(session);
             }
         ```

    2. 通过控制器的形参获取请求参数

       - Controller 中的业务方法参数名称与请求参数的名称一致，参数值会自动映射匹配

         ```java
             /**
              * 获得基本类型参数
              * @param username
              * @param age
              * @throws IOException
              */
             //Controller中的业务方法参数名称与请求参数的name一致，参数值会自动映射匹配
             //http://localhost:8080/Spring_mvc/user/quick10?username=lhk&age=21
             @RequestMapping(value="/quick10")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save10(String username,int age) throws IOException {
                 System.out.println(username);
                 System.out.println(age);
             }
         ```

       - 当请求的参数名称与 Controller 的业务方法参数名称不一致时，可以使用`@RequestParam`注解将请求参数和方法中的形参显示的进行绑定

         - value 设置请求参数的名称
         - required = true 表示必须传一个请求参数，否则报 400 的错误
         - defaultValue 设置形参的默认值

         ```java
             /**
              * 当请求的参数名称与Controller的业务方法参数名称不一致时，
              * 就需要使用@RequestParam注解显示的绑定
              * @param username
              * @throws IOException
              */
             @RequestMapping(value="/quick15")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save15(@RequestParam(value = "name" , required = false,defaultValue = "lhk") String username) throws IOException {
                 System.out.println(username);
             }
         ```

       - `@RequestHeader`：将请求头信息和控制器方法的形参进行绑定

         ```java
             /**
              * 获取请求头的信息
              * @param user_agent
              * @throws IOException
              */
             @RequestMapping(value="quick19")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save19(@RequestHeader(value = "User-Agent") String user_agent) throws IOException {
                 System.out.println(user_agent);
             }
         ```

       - `@CookieValue`：将 cookie 数据和控制器方法的形参进行绑定

         ```java
             /**
              * 通过注解@CookieValue获取cookie的值
              * @param jsessionid
              * @throws IOException
              */
             @RequestMapping(value="quick20")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save20(@CookieValue(value = "JSESSIONID") String jsessionid) throws IOException {
                 System.out.println(jsessionid);
             }
         ```

       - 通过 pojo 获取请求参数

         - 控制器方法的实体类类型形参的属性名和请求参数名保持一致，即可将请求参数映射到实体类类型的形参中

         ```java
             /**
              * 获得pojo类型参数
              * @param user
              * @throws IOException
              */
             //Controller中的业务方法中的pojo参数属性名称与请求参数的name一致，参数值会自动映射匹配
             //http://localhost:8080/Spring_mvc/user/quick11?name=lhk&age=21
             @RequestMapping(value="/quick11")
             @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
             public void save11(User user) throws IOException {
                 System.out.println(user);
             }
         ```

    3. 解决获取请求参数乱码问题

       - 在设置编码执行之前，不能获取任何的请求参数，如果先获取了请求参数，再设置编码将不会生效

       - spring MVC 处理编码的过滤器一定要设置在其他过滤器之前，否则无效

       - 在 web.xml 文件中配置全局过滤器

         ```xml
             <!--配置全局过滤的filter 解决post请求乱码问题-->
             <filter>
                 <filter-name>CharacterEncodingFilter</filter-name>
                 <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
                 <!-- 设置请求的编码 -->
                 <init-param>
                     <param-name>encoding</param-name>
                     <param-value>utf-8</param-value>
                 </init-param>
                 <!-- 设置响应的编码 -->
                 <init-param>
                     <param-name>forceEncoding</param-name>
                     <param-value>true</param-value>
                 </init-param>
             </filter>
             <filter-mapping>
                 <filter-name>CharacterEncodingFilter</filter-name>
                 <url-pattern>/*</url-pattern>
             </filter-mapping>
         ```

### 6. 往域对象中共享数据



### 7 spring MVC 的整个流程

1. 浏览器发送请求
2. 检查请求地址是否与 web.xml 文件中设置的 `url-pattern` 相匹配，匹配成功则将该请求交由 DispatcherServlet  进行处理
3. 前端控制器 DispatcherServlet  读取 SpringMvc 的核心配置文件，通过组件扫描找到 controller (控制器)，检查请求地址是否与控制器中的 `@RequestMapping` 注解中的 value 值相匹配，匹配成功则该注解所标识的 controller (控制器)方法会对该请求进行处理
4. controller 方法需要返回一个字符串的类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，并对该视图对应的页面进行渲染，最终将请求转发到视图所对应的页面