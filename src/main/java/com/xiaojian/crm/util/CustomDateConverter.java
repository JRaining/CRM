package com.xiaojian.crm.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期类型绑定
 */
public class CustomDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        // 实现将字符串转换成日期类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(s);
        } catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
