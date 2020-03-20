package com.jcheck.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    //禁止实例化
    private DateUtil(){}

    public static String dateToString(Date date,String format){
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date stringToDate(String dateString,String format) throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat(format);
        return sdf.parse(dateString);
    }
}
