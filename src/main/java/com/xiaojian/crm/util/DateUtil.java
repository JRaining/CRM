package com.xiaojian.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    // 日期转字符串
    public static String dateToStr(Date date,String patt){

        SimpleDateFormat sd = new SimpleDateFormat(patt);
        String format = sd.format(date);

        return format;
    }

    // 字符串转日期
    public static Date strToDate(String dateStr,String patt) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(dateStr);

        return date;
    }

}
