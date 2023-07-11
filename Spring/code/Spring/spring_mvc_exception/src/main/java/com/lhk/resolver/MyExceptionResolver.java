package com.lhk.resolver;

import com.lhk.exception.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TheMutents
 * @creat on 2021-12-23-18:47
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e 异常对象
     * @return ModelAndView 跳转到错误视图信息页面
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof ClassCastException){
            modelAndView.addObject("info","类转换异常");
        }else if (e instanceof MyException){
            modelAndView.addObject("info","自定义异常");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
