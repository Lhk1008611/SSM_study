package com.lhk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhk.domain.User;
import com.lhk.domain.VO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * 多文件上传,表单中文件名相同（传入参数MultipartFile[]）
 *
 * @author TheMutents
 * @creat on 2021-12-19-11:27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 文件下载
     * @param httpSession
     * @return ResponseEntity
     * @throws IOException
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(
            HttpSession httpSession
    ) throws IOException {
        //1. 获取需要下载的文件路径
        ServletContext servletContext = httpSession.getServletContext();
        String imagePath = servletContext.getRealPath("image");
        String filePath = imagePath + File.separator + "appreciate_afdian.png";
        //2. 创建输入流读取文件字节
        FileInputStream fileInputStream = new FileInputStream(filePath);
        byte[] bytes = new byte[fileInputStream.available()];
        //将文件流读入字节数组
        fileInputStream.read(bytes);
        //3. 创建 HttpHeaders 对象，设置头信息
        MultiValueMap<String,String> httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename=appreciate_afdian.png");
        //4. 返回 ResponseEntity 对象，作为响应报文响应到浏览器
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes,httpHeaders,HttpStatus.OK);
        fileInputStream.close();
        return responseEntity;
    }

    /**
     * 多文件上传,表单中文件名不同
     * 单文件上传，传入一个MultipartFile参数即可
     *
     * @param uploadFile
     * @throws IOException
     */
    @RequestMapping(value = "upload")
    public String uploadFile(
            MultipartFile[] uploadFile,
            HttpSession httpSession
    ) throws IOException {
        ServletContext servletContext = httpSession.getServletContext();
        String imagePath = servletContext.getRealPath("image");
        File file = new File(imagePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //将文件保存到磁盘
        for (MultipartFile multipartFile : uploadFile) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            String filename = multipartFile.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            filename = uuid + suffix;
            multipartFile.transferTo(new File(imagePath + File.separator + filename));
        }
        return "success";
    }


    /**
     * 通过注解@CookieValue获取cookie的值
     *
     * @param jsessionid
     * @throws IOException
     */
    @RequestMapping(value = "quick20")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save20(@CookieValue(value = "JSESSIONID") String jsessionid) throws IOException {
        System.out.println(jsessionid);
    }

    /**
     * 获取请求头的信息
     *
     * @param user_agent
     * @throws IOException
     */
    @RequestMapping(value = "quick19")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save19(@RequestHeader(value = "User-Agent") String user_agent) throws IOException {
        System.out.println(user_agent);
    }

    /**
     * SpringMVC中获取Servlet的相关API
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "quick18")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save18(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }

    /**
     * 将请求中日期字符串参数转化为日期对象注入业务方法参数中
     *
     * @param date
     * @throws IOException
     */
    @RequestMapping(value = "/quick17")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save17(Date date) throws IOException {
        System.out.println(date);
    }

    /**
     * 获得Restful风格的参数
     * Restful风格：http://localhost:8080/Spring_mvc/user/quick16/lhk
     * 在业务方法中使用@PathVariable注解进行占位符的匹配获取工作
     *
     * @param username
     * @throws IOException
     */
    @RequestMapping(value = "/quick16/{username}")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save16(@PathVariable(value = "username") String username) throws IOException {
        System.out.println(username);
    }


    /**
     * 当请求的参数名称与Controller的业务方法参数名称不一致时，
     * 就需要使用@RequestParam注解显示的绑定
     *
     * @param username
     * @throws IOException
     */
    @RequestMapping(value = "/quick15")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save15(@RequestParam(value = "name", required = false, defaultValue = "lhk") String username) throws IOException {
        System.out.println(username);
    }

    /**
     * 使用ajax提交时，可以指定contentType为json形式
     * 那么在业务方法参数位置使用@RequestBody可以直接接收集合数据而无需对集合进行pojo包装
     *
     * @param userlist
     * @throws IOException
     */
    @RequestMapping(value = "/quick14")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save14(@RequestBody List<User> userlist) throws IOException {
        System.out.println(userlist);
    }

    /**
     * 获取集合类型参数(表单提交)
     * 需要给集合包装到一个pojo类（VO）中
     *
     * @param vo
     * @throws IOException
     */
    @RequestMapping(value = "/quick13")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save13(VO vo) throws IOException {
        System.out.println(vo);
    }

    /**
     * 获得数组类型参数
     *
     * @param strs
     * @throws IOException
     */
    //Controller中的业务方法中的数组参数名称与请求参数的name一致，参数值会自动映射匹配
    //http://localhost:8080/Spring_mvc/user/quick12?strs=123&strs=456&strs=789
    @RequestMapping(value = "/quick12")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save12(String[] strs) throws IOException {
        System.out.println(Arrays.asList(strs));
    }

    /**
     * 获得pojo类型参数
     *
     * @param user
     * @throws IOException
     */
    //Controller中的业务方法中的pojo参数属性名称与请求参数的name一致，参数值会自动映射匹配
    //http://localhost:8080/Spring_mvc/user/quick11?name=lhk&age=21
    @RequestMapping(value = "/quick11")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save11(User user) throws IOException {
        System.out.println(user);
    }

    /**
     * 获得基本类型参数
     *
     * @param username
     * @param age
     * @throws IOException
     */
    //Controller中的业务方法参数名称与请求参数的name一致，参数值会自动映射匹配
    //http://localhost:8080/Spring_mvc/user/quick10?username=lhk&age=21
    @RequestMapping(value = "/quick10")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public void save10(String username, int age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }

    /**
     * 利用SpringMVC自动将User转为JSON格式的字符串
     * 需要在spring-mvc.xml中配置处理器映射器  RequestMappingHandlerAdapter
     * 进行该配置比较繁琐,可以使用mvc的注解驱动代替上述繁琐的配置
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/quick9")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public User save9() throws IOException {
        User user = new User();
        user.setName("lhk");
        user.setAge(21);
        return user;
    }


    @RequestMapping(value = "/quick8")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public String save8() throws IOException {
        User user = new User();
        user.setName("lhk");
        user.setAge(21);
        //使用Json转换工具将数据转换成Json格式字符串，再进行数据回写
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    //使用return回写数据，需要添加注解@ResponseBody
    @RequestMapping(value = "/quick7")
    @ResponseBody  //该注解告知SpringMVC框架，不进行视图跳转，直接进行数据响应
    public String save7() throws IOException {
        return "hello World";
    }


    //通过传入SpringMVC提供的HttpServletResponse对象进行回写数据
    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello world ");
    }


    //方法中传入SpringMVC提供的HttpServletRequest对象参数
    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest request) {
        request.setAttribute("username", "大时代");
        return "success";
    }

    //方法中传入SpringMVC提供的Model对象参数，进行转发和重定向
    @RequestMapping(value = "/quick4")
    public String save4(Model model) {
        model.addAttribute("username", "lhk1008611");
        return "success";
    }

    // modelMap 的使用
    @RequestMapping(value = "/modelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("username", "lhk1008611");
        return "success";
    }

    // map 的使用
    @RequestMapping(value = "/map")
    public String testModelMap(Map<String, Object> map) {
        map.put("username", "lhk1008611");
        return "success";
    }


    //方法中传入SpringMVC提供的ModelAndView对象参数，进行转发和重定向
    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView) {
        //设置模型数据
        modelAndView.addObject("username", "LHK");
        //设置视图名称(ViewName)
        modelAndView.setViewName("success");
        return modelAndView;
    }


    //通过返回ModelAndView对象进行转发和重定向
    @RequestMapping(value = "/quick2")
    public ModelAndView save2() {
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username", "TheMetents");
        //设置视图名称(ViewName)
        modelAndView.setViewName("success");
        return modelAndView;
    }

    //通过return进行转发和重定向
    @RequestMapping(value = "/quick", method = RequestMethod.GET, params = {"username"})
    public String save() {
        System.out.println("Controller save running....");
        return "success";
    }

    @RequestMapping(
            value = "/a?b/test1",
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username", "!user", "age=21", "sex!=1"}
    )
    public String test() {
        return "success";
    }

    //往session域中共享数据
    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        session.setAttribute("key", "value");
        return "success";
    }

    //往application域中共享数据
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("key", "value");
        return "success";
    }


}
