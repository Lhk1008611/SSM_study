package com.lhk.convert;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器
 * SpringMVC默认已经提供了一些常用的类型转换器，例如客户端提交的字符串转换成int型进行参数设置。
 * 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如:日期类型的数据就需要自定义转换器。
 *
 * 自定义类型转换器的开发步骤:
 * 1定义转换器类实现Converter接口
 * 2.在配置文件中声明转换器
 * 3.在<annotation-driven>中引用转换器
 *
 * @author TheMutents
 * @creat on 2021-12-20-16:40
 */
public class DateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        //将日期字符串转化为日期对象
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
             date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
